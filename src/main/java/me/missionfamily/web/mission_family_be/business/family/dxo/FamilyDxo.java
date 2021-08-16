package me.missionfamily.web.mission_family_be.business.family.dxo;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;

import javax.validation.Valid;

public class FamilyDxo {

    public static class Request implements MissionRequest {
        @Valid
        @JsonProperty("family")
        private FamilyModel family;

        @Valid
        @JsonProperty("account")
        private AccountModel account;
    }

    public static class Response implements MissionResponse {

        @Valid
        @JsonProperty("result")
        private ResponseModel result;

    }
}
