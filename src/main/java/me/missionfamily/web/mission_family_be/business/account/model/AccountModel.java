package me.missionfamily.web.mission_family_be.business.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    @NotNull
    private String userId;
    @NotNull
    private String userPassword;
    @NotEmpty
    private String userName;

}
