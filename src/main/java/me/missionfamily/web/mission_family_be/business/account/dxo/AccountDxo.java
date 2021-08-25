package me.missionfamily.web.mission_family_be.business.account.dxo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.domain.Account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
public class AccountDxo {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements MissionRequest {

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("user_id")
        private String userId;

        @JsonInclude(Include.NON_NULL)
        private String password;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("user_name")
        private String userName;

        @Valid
        @JsonProperty("account")
        private AccountModel account;

        @Override
        public AccountModel account() {
            return this.account;
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response implements MissionResponse {

        @NotNull
        @JsonProperty("result")
        private ResponseModel result;

        @JsonProperty("checked_id")
        @JsonInclude(Include.NON_NULL)
        private String checkedId;

        @Valid
        @JsonProperty("account")
        @JsonInclude(Include.NON_NULL)
        private AccountModel account;

        @Override
        public int getCode() {
            return result.getCode();
        }
    }
}
