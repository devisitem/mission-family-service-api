package me.missionfamily.web.mission_family_be.common.logging.context;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

public class LoggerContextHolder {

    public static final String MODE_THREADLOCAL = "MODE_THREADLOCAL";

    public static final String MODE_INHERITABLETHREADLOCAL = "MODE_INHERITABLETHREADLOCAL";

    public static final String MODE_GLOBAL = "MODE_GLOBAL";

    public static final String SYSTEM_PROPERTY = "mission.logger.strategy";

    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);

    private static LoggerContextHolderStrategy strategy;

    private static int initializeCount = 0;

    static {
        initialize();
    }

    private static void initialize() {
        if( ! StringUtils.hasText(strategyName)) {
            strategyName = MODE_THREADLOCAL;
        }

        if(strategyName.equals(MODE_THREADLOCAL)) {
            strategy = new ThreadLocalLoggerContextHolderStrategy();
        }
        else if (strategyName.equals(MODE_INHERITABLETHREADLOCAL)) {
            strategy = new InheritableThreadLocalLoggerContextHolderStrategy();
        }
        else if (strategyName.equals(MODE_GLOBAL)) {
            strategy = new GlobalLoggerContextHolderStrategy();
        }
        else {
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (LoggerContextHolderStrategy) customStrategy.newInstance();
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
        }
        initializeCount++;
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static LoggerContext getContext() {
        return strategy.getContext();
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    public static void setContext(LoggerContext context) {
        strategy.setContext(context);
    }

    public static void setStrategyName(String strategyName) {
        LoggerContextHolder.strategyName = strategyName;
        initialize();
    }

    public static LoggerContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    public static LoggerContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

    @Override
    public String toString() {
        return "LoggerContextHolder[strategy='" + strategyName + "'; initializeCount=" + initializeCount + "]";
    }
}
