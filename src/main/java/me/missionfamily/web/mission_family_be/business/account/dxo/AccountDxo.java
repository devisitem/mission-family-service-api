package me.missionfamily.web.mission_family_be.business.account.dxo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
public class AccountDxo {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements me.missionfamily.web.mission_family_be.common.data_transfer.Request {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("user_id")
        private String userId;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String password;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("user_name")
        private String userName;


    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response implements me.missionfamily.web.mission_family_be.common.data_transfer.Response {

        @Valid
        @NotNull
        @JsonProperty("result")
        private ResponseModel result;

        @JsonProperty("checked_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String checkedId;

    }
}
