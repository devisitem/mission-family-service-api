package me.missionfamily.web.mission_family_be.common.logging.context;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;

public class AppropriateStrategyProvider {

    private AppropriateStrategyProvider(){}
    private static final AppropriateStrategyProvider provider = new AppropriateStrategyProvider();

    public static AppropriateStrategyProvider getInstance() {
        return provider;
    }

    public TrackerContextHolderStrategy provide(String strategyName) {
        TrackerContextHolderStrategy strategy = null;

        if(strategyName.equals(TrackerContextHolder.MODE_THREADLOCAL)) {
            strategy = new ThreadLocalTrackerContextHolderStrategy();
        }
        else if (strategyName.equals(TrackerContextHolder.MODE_INHERITABLETHREADLOCAL)) {
            strategy = new InheritableThreadLocalTrackerContextHolderStrategy();
        }
        else if (strategyName.equals(TrackerContextHolder.MODE_GLOBAL)) {
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

        return strategy;
    }
}
