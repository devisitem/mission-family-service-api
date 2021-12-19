package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.springframework.util.Assert;

public class GlobalLoggerContextHolderStrategy implements LoggerContextHolderStrategy{

    private static LoggerContext contextHolder;

    @Override
    public void clearContext() {
        contextHolder = null;
    }

    @Override
    public LoggerContext getContext() {
        if(Utils.isNull(contextHolder)) {
            contextHolder = new LoggerContextImpl();
        }
        return contextHolder;
    }

    @Override
    public void setContext(LoggerContext context) {
        Assert.notNull(context, "Only non-null LoggerContext instances are permitted");
        contextHolder = context;
    }

    @Override
    public LoggerContext createEmptyContext() {
        return new LoggerContextImpl();
    }
}
