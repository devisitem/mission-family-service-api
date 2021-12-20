package me.missionfamily.web.mission_family_be.common.logging.context;

public interface TrackerContextHolderStrategy {

    void clearContext();

    TrackerContext getContext();

    void setContext(TrackerContext context);

    TrackerContext createEmptyContext();
}
