package me.missionfamily.web.mission_family_be.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepLogger {
    String ROOT_LOGGER_NAME = "STEP_LOGGER";

    public String getName(){
        return this.ROOT_LOGGER_NAME;
    }

    public void trace(String message) {
        log.trace(message);
    }
    public void trace(String message, Object argument) {
        log.trace(message, argument);
    }
    public void trace(String message, Object argument1, Object argument2) {
        log.trace(message, argument1, argument2);
    }
    public void trace(String message, Object... arguments) {
        log.trace(message, arguments);
    }
    public void trace(String message, Throwable throwable) {
        log.trace(message, throwable);
    }

    public void debug(String message) {
        log.debug(message);
    }
    public void debug(String message, Object argument) {
        log.debug(message, argument);
    }
    public void debug(String message, Object argument1, Object argument2) {
        log.debug(message, argument1, argument2);
    }
    public void debug(String message, Object... arguments) {
        log.debug(message, arguments);
    }
    public void debug(String message, Throwable throwable) {
        log.debug(message, throwable);
    }

    public void info(String message) {
        log.info(message);
    }
    public void info(String message, Object argument) {
        log.info(message, argument);
    }
    public void info(String message, Object argument1, Object argument2) {
        log.info(message, argument1, argument2);
    }
    public void info(String message, Object... arguments) {
        log.info(message, arguments);
    }
    public void info(String message, Throwable throwable) {
        log.info(message, throwable);
    }


    public void error(String message) {
        log.error(message);
    }
    public void error(String message, Object argument) {
        log.error(message, argument);
    }
    public void error(String message, Object argument1, Object argument2) {
        log.error(message, argument1, argument2);
    }
    public void error(String message, Object... arguments) {
        log.error(message, arguments);
    }
    public void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }
}
