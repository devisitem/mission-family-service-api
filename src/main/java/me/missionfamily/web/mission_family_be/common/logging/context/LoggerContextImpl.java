package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.MissionLogger;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.ObjectUtils;

public class LoggerContextImpl implements LoggerContext{

    private MissionLogger logger;


    public LoggerContextImpl() {}

    public LoggerContextImpl(MissionLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LoggerContextImpl) {
            LoggerContextImpl other = (LoggerContextImpl) obj;
            if(Utils.isNull(this.getLogger()) && Utils.isNull(other.getLogger())) {
                return true;
            }
            if(Utils.isNotNull(this.getLogger()) && Utils.isNotNull(other.getLogger()) &&
                    this.getLogger().equals(other.getLogger())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (Utils.isNull(this.logger)){
            builder.append("Null Logger");
        } else {
            builder.append("logger = ").append(this.logger);
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.logger);
    }

    @Override
    public MissionLogger getLogger() {
        return this.logger;
    }

    @Override
    public void setLogger(MissionLogger logger) {
        this.logger = logger;
    }
}
