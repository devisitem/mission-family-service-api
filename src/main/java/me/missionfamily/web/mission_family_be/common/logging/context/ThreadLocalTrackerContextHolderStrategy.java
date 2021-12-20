package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.Assert;

final class ThreadLocalTrackerContextHolderStrategy implements TrackerContextHolderStrategy {

    private static final ThreadLocal<TrackerContext> contextHolder = new ThreadLocal<>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public TrackerContext getContext() {
        TrackerContext context = contextHolder.get();
        if(Utils.isNull(context)) {
            context = createEmptyContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void setContext(TrackerContext context) {
        Assert.notNull(context, "Only non-null TrackerContext instances are permitted");
        contextHolder.set(context);
    }

    @Override
    public TrackerContext createEmptyContext() {
        return new TrackerContextImpl();
    }
}
