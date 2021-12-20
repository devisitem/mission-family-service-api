package me.missionfamily.web.mission_family_be.common.logging.layout;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.util.CachingDateFormatter;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContextHolder;
import me.missionfamily.web.mission_family_be.common.logging.tracker.StepLogTracker;
import me.missionfamily.web.mission_family_be.common.util.Utils;


public class StepLogLayout extends LayoutBase<ILoggingEvent> {

    CachingDateFormatter formatter = new CachingDateFormatter("HH:mm:ss.SSS");
    ThrowableProxyConverter converter = new ThrowableProxyConverter();

    @Override
    public void start() {
        System.out.println("Start Logg Layout");
        converter.start();
        super.start();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder builder = new StringBuilder();
        try {
            if (!isStarted()) {
                return CoreConstants.EMPTY_STRING;
            }
            StepLogTracker tracker = TrackerContextHolder.getContext().getTracker();

            long timeStamp = event.getTimeStamp();
            builder
                    .append(formatter.format(timeStamp))
                    .append(" ").append(String.format("%5s", event.getLevel().toString())).append(" - ")
                    .append("[").append(tracker.getAllStepString()).append("]");

            IThrowableProxy proxy = event.getThrowableProxy();
            builder.append(event.getFormattedMessage()).append(CoreConstants.LINE_SEPARATOR);
            if (Utils.isNotNull(proxy)) {
                builder.append(converter.convert(event));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return builder.toString();
    }

}


