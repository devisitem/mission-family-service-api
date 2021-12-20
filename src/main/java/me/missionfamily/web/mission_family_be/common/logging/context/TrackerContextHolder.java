package me.missionfamily.web.mission_family_be.common.logging.context;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

public class TrackerContextHolder {

    public static final String MODE_THREADLOCAL = "MODE_THREADLOCAL";

    public static final String MODE_INHERITABLETHREADLOCAL = "MODE_INHERITABLETHREADLOCAL";

    public static final String MODE_GLOBAL = "MODE_GLOBAL";

    public static final String SYSTEM_PROPERTY = "mission.tracker.strategy";

    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);

    private static TrackerContextHolderStrategy strategy;

    private static int initializeCount = 0;


    static {
        initialize();
    }

    private static void initialize() {
        if( ! StringUtils.hasText(strategyName)) {
            strategyName = MODE_THREADLOCAL;
        }

        if(strategyName.equals(MODE_THREADLOCAL)) {
            strategy = new ThreadLocalTrackerContextHolderStrategy();
        }
        else if (strategyName.equals(MODE_INHERITABLETHREADLOCAL)) {
            strategy = new InheritableThreadLocalTrackerContextHolderStrategy();
        }
        else if (strategyName.equals(MODE_GLOBAL)) {
            strategy = new GlobalTrackerContextHolderStrategy();
        }
        else {
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (TrackerContextHolderStrategy) customStrategy.newInstance();
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
        }
        initializeCount++;
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static TrackerContext getContext() {
        return strategy.getContext();
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    public static void setContext(TrackerContext context) {
        strategy.setContext(context);
    }

    public static void setStrategyName(String strategyName) {
        TrackerContextHolder.strategyName = strategyName;
        initialize();
    }

    public static TrackerContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    public static TrackerContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

    @Override
    public String toString() {
        return "LoggerContextHolder[strategy='" + strategyName + "'; initializeCount=" + initializeCount + "]";
    }
}
