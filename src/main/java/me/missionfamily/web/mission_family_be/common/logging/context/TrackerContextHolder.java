package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.tracker.StepLogTracker;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

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
        String classLoaderName = TrackerContextHolder.class.getClassLoader().getClass().getName();
        System.out.println("class loader" + classLoaderName);
    }

    private TrackerContextHolder(){}

    private static void initialize() {
        if( ! StringUtils.hasText(strategyName)) {
            strategyName = MODE_THREADLOCAL;
        }
        strategy = AppropriateStrategyProvider.getInstance().provide(strategyName);
        initializeCount++;
        System.out.println("proceed initialize() with initializeCount ["+initializeCount+"] in the TrackerContextHolder");
    }

    public static void clearContext() {
        strategy.clearContext();
        System.out.println("clear Context in the TrackerContextHolder");
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
        TrackerContext emptyContext = strategy.createEmptyContext();
        emptyContext.setTracker(new StepLogTracker());
        return emptyContext;
    }

    @Override
    public String toString() {
        return "LoggerContextHolder[strategy='" + strategyName + "'; initializeCount=" + initializeCount + "]";
    }

}
