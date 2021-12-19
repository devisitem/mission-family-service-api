package me.missionfamily.web.mission_family_be.common.logging.context;

public interface LoggerContextHolderStrategy {

    void clearContext();

    LoggerContext getContext();

    void setContext(LoggerContext context);

    LoggerContext createEmptyContext();
}
