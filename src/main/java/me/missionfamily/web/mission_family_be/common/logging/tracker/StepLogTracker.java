package me.missionfamily.web.mission_family_be.common.logging.tracker;

import me.missionfamily.web.mission_family_be.common.logging.StepLogger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StepLogTracker {

    private String txId;
    private List<String> serviceStep;


    public StepLogTracker init(HttpServletRequest request) {
        return this;
    }
}
