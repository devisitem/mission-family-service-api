package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.tracker.StepLogTracker;

public interface TrackerContext {

    StepLogTracker getTracker();

    void setTracker(StepLogTracker tracker);
}
