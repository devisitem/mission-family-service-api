package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerContext {

    private LoggerContext(){}

    private static final ThreadLocal<LoggerAttribute> loggerAttribute = new ThreadLocal<LoggerAttribute>() {

        @Override
        protected LoggerAttribute initialValue() {
            return new LoggerAttribute();
        }
    };

    private static final InheritableThreadLocal<LoggerAttribute> inHeritableLoggerAttributes = new InheritableThreadLocal<LoggerAttribute>();

    public static void applyLogObject(StepLogger step) {
        applyLogObject(step, false);
    }

    public static void applyLogObject(StepLogger step, boolean inheritable) {
        try {
            if (inheritable) {
                loggerAttribute.remove();

                if (MissionUtil.isNull(inHeritableLoggerAttributes.get())) {

                    LoggerAttribute attribute = new LoggerAttribute(step);
                    inHeritableLoggerAttributes.set(attribute);
                } else {
                    LoggerAttribute attribute = inHeritableLoggerAttributes.get();
                    attribute.setLogObject(step);
                }
            } else {
                inHeritableLoggerAttributes.remove();
                if (MissionUtil.isNull(loggerAttribute.get())) {

                    LoggerAttribute attribute = new LoggerAttribute(step);
                    loggerAttribute.set(attribute);
                } else {
                    LoggerAttribute attribute = loggerAttribute.get();
                    attribute.setLogObject(step);
                    loggerAttribute.set(attribute);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LoggerAttribute getAttribute() {
        LoggerAttribute attribute = loggerAttribute.get();
        if(MissionUtil.isNull(attribute)) {
            return inHeritableLoggerAttributes.get();
        }
        return attribute;
    }

    private static void setAttribute(LoggerAttribute attribute, boolean inheritable) {
        if(MissionUtil.isNull(attribute)) {
            resetAttributes();
        } else {
            if(inheritable) {
                inHeritableLoggerAttributes.set(attribute);
                loggerAttribute.remove();
            } else {
                loggerAttribute.set(attribute);
                inHeritableLoggerAttributes.remove();
            }
        }
    }


    public static void resetAttributes() {
        loggerAttribute.remove();
        inHeritableLoggerAttributes.remove();
    }

    public static StepLogger getStepLogger() {
        if(MissionUtil.isNull(loggerAttribute.get().getStep())) {
            return inHeritableLoggerAttributes.get().getStep();
        }
        return loggerAttribute.get().getStep();
    }
}
