package me.missionfamily.web.mission_family_be.common.logging.context;

import me.missionfamily.web.mission_family_be.common.logging.StepLogger;

public class LoggerAttribute {
    private StepLogger step;

    public LoggerAttribute() {

    }

    public LoggerAttribute(StepLogger step) {
        this.step = step;
    }

    public void setLogObject(StepLogger step) {
        this.step = step;
    }

    public StepLogger getStep() {
        return this.step;
    }
}
