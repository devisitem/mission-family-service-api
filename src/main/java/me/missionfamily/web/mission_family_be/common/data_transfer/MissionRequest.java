package me.missionfamily.web.mission_family_be.common.data_transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;

import javax.validation.Valid;

public interface MissionRequest {

    AccountModel account();
}
