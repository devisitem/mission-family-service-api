package me.missionfamily.web.mission_family_be.common.validator;

import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public class StingValidator implements MissionValidator{

    private String target;
    private String regExp;
    private ServiceException error;

    @Override
    public MissionValidator validate(Object target, DeclaredClassification manner) throws ServiceException {

        return null;
    }

    @Override
    public MissionValidator result() throws ServiceException {
        return null;
    }

    @Override
    public boolean isValidated() throws ServiceException {
        return false;
    }

}
