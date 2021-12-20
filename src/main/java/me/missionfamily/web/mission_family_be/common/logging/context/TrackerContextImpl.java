package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.MissionLogger;
import me.missionfamily.web.mission_family_be.common.logging.tracker.StepLogTracker;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.ObjectUtils;

public class TrackerContextImpl implements TrackerContext {

    private StepLogTracker tracker;


    public TrackerContextImpl() {}

    public TrackerContextImpl(StepLogTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TrackerContextImpl) {
            TrackerContextImpl other = (TrackerContextImpl) obj;
            if(Utils.isNull(this.getTracker()) && Utils.isNull(other.getTracker())) {
                return true;
            }
            if(Utils.isNotNull(this.getTracker()) && Utils.isNotNull(other.getTracker()) &&
                    this.getTracker().equals(other.getTracker())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (Utils.isNull(this.tracker)){
            builder.append("Null Logger");
        } else {
            builder.append("logger = ").append(this.tracker);
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.tracker);
    }

    @Override
    public StepLogTracker getTracker() {
        return this.tracker;
    }

    @Override
    public void setTracker(StepLogTracker tracker) {
        this.tracker = tracker;
    }
}
