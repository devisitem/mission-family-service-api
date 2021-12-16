package me.missionfamily.web.mission_family_be.common.logging.layout;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.util.CachingDateFormatter;
import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.logging.context.LoggerContext;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import org.springframework.stereotype.Component;


public class StepLogLayout extends LayoutBase<ILoggingEvent> {

    CachingDateFormatter formatter = new CachingDateFormatter("HH:mm:ss.SSS");
    ThrowableProxyConverter converter = new ThrowableProxyConverter();

    @Override
    public void start() {
        System.out.println("Start Logg Layout");
        converter.start();
        super.start();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder builder = new StringBuilder();

        System.out.println("Start Logging");
        if (!isStarted()) {
            return CoreConstants.EMPTY_STRING;
        }
        StepLogger stepLogger = LoggerContext.getStepLogger();
        StepLogger step = LoggerContext.getAttribute().getStep();
        long timeStamp = event.getTimeStamp();
        builder
                .append(formatter.format(timeStamp))
                .append(" ").append(String.format("%5s", event.getLevel().toString())).append(" - ")
                .append("[").append(stepLogger.getStep()).append("]");

        IThrowableProxy proxy = event.getThrowableProxy();
        builder.append(event.getFormattedMessage()).append(CoreConstants.LINE_SEPARATOR);
        if (MissionUtil.isNotNull(proxy)) {
            builder.append(converter.convert(event));
        }
        return builder.toString();
    }


}


