package me.missionfamily.web.mission_family_be.common.logging.service;

import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContext;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContextHolder;
import me.missionfamily.web.mission_family_be.common.util.RandomStringUtils;

public class LoggerService {

    public static void init() throws Exception {

        TrackerContextHolder.clearContext();

        TrackerContext context = TrackerContextHolder.createEmptyContext();
        TrackerContextHolder.setContext(context);
        TrackerContextHolder.getContext().getTracker().init(RandomStringUtils.randomAlphanumeric(12));
    }
}
