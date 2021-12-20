package me.missionfamily.web.mission_family_be.common.logging.tracker;

import io.jsonwebtoken.lang.Assert;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.service_enum.LogStep;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class StepLogTracker {

    private String txId;
    private List<LogStep> serviceStep;

    public void init(String txId) {
        this.txId = txId;
        this.serviceStep = new Vector<>();
    }

    public void addStep(LogStep step) {
        Assert.notNull(step, "Step cannot be null.");
        serviceStep.add(step);
    }

    public String getAllStepString() {
        return Arrays.toString(this.serviceStep.stream().toArray());
    }
}
