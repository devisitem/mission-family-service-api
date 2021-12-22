package me.missionfamily.web.mission_family_be.common.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.common.exception.ExceptionModel;
import me.missionfamily.web.mission_family_be.common.exception.MissionErrorResponse;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.logging.StepLogger;
import me.missionfamily.web.mission_family_be.common.logging.context.TrackerContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
@RequiredArgsConstructor
public class MissionExceptionHandler {

    private final StepLogger step;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<MissionErrorResponse> handleServiceException(ServiceException e) {

        step.error(e.getMessage());

        return ResponseEntity.status(e.getStatus().getStatus()).body(MissionErrorResponse.builder()
                .exception(ExceptionModel.builder()
                        .errorCode(e.getStatus().getCode())
                        .serverMessage(e.getStatus().getMessage())
                        .build())
                .build());
    }
}
