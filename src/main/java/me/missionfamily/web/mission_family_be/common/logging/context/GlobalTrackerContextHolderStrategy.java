package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.Assert;

final class GlobalTrackerContextHolderStrategy implements TrackerContextHolderStrategy {

    private static TrackerContext contextHolder;

    @Override
    public void clearContext() {
        contextHolder = null;
    }

    @Override
    public TrackerContext getContext() {
        if(Utils.isNull(contextHolder)) {
            contextHolder = new TrackerContextImpl();
        }
        return contextHolder;
    }

    @Override
    public void setContext(TrackerContext context) {
        Assert.notNull(context, "Only non-null TrackerContext instances are permitted");
        contextHolder = context;
    }

    @Override
    public TrackerContext createEmptyContext() {
        return new TrackerContextImpl();
    }
}
