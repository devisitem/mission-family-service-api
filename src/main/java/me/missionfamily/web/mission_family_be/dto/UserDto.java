package me.missionfamily.web.mission_family_be.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDto {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPassword;
    @NotEmpty
    private String userName;

}