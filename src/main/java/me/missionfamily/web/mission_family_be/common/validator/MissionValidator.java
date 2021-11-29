package me.missionfamily.web.mission_family_be.common.validator;


import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public interface MissionValidator {

    MissionValidator validate(Object target, DeclaredClassification manner) throws ServiceException;

    MissionValidator result() throws ServiceException;

    boolean isValidated() throws ServiceException;

}
