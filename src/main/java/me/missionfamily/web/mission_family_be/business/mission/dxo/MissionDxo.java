package me.missionfamily.web.mission_family_be.business.mission.dxo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.mission.model.MissionModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;

import javax.validation.Valid;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
public class MissionDxo {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements MissionRequest {

        @Valid
        @JsonProperty("mission")
        @JsonInclude(Include.NON_NULL)
        private MissionModel mission;

        @Valid
        @JsonProperty("account")
        private AccountModel account;

        @Override
        public AccountModel account() {
            return this.account;
        }
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response implements MissionResponse {

        @Valid
        @JsonProperty("result")
        ResponseModel result;

        @Override
        public int getResultCode() {
            return result.getResultCode();
        }
    }





}
