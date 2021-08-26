package me.missionfamily.web.mission_family_be.business.account.dxo;

import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
public class UserDxo {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Request implements MissionRequest {

        @Valid
        @NotNull
        private AccountModel account;

        @Override
        public AccountModel account() {
            return this.account;
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response implements MissionResponse {

        @Valid
        @NotNull
        private ResponseModel result;

        @Override
        public int code() {
            return result.getCode();
        }
    }


}