package me.missionfamily.web.mission_family_be.business.account.dxo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
public class UserDxo {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Request implements me.missionfamily.web.mission_family_be.common.data_transfer.Request {

        @Valid
        @NotNull
        private AccountModel account;


    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response implements me.missionfamily.web.mission_family_be.common.data_transfer.Response {

        @Valid
        @NotNull
        private ResponseModel responseModel;



    }


}