package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.MissionLogger;

public interface LoggerContext {

    MissionLogger getLogger();

    void setLogger(MissionLogger logger);
}
