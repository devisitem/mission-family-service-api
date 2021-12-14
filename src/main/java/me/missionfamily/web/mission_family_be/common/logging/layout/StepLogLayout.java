package me.missionfamily.web.mission_family_be.common.logging.layout;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.util.CachingDateFormatter;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;

public class StepLogLayout extends LayoutBase<ILoggingEvent> {

    CachingDateFormatter formatter = new CachingDateFormatter("yyyy-MM-dd HH:mm:ss.SSS");
    ThrowableProxyConverter converter = new ThrowableProxyConverter();

    @Override
    public void start() {
        converter.start();
        super.start();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        StepLogger logger = new StepLogger();
        if( ! isStarted()) {
            return CoreConstants.EMPTY_STRING;
        }

        StringBuilder builder = new StringBuilder();
        long timeStamp = event.getTimeStamp();
        builder
                .append("[").append(event.getTimeStamp() - event.getLoggerContextVO().getBirthTime()).append("]")
                .append("[").append(formatter.format(timeStamp)).append("]")
                .append("[").append(event.getLevel()).append("]");

        if(MissionUtil.isNotNull(logger)) {
            builder.append(event.getFormattedMessage());
        }
        System.out.println("로거 수행 메세지는? = "+event.getFormattedMessage());
        return builder.toString();
    }
}


