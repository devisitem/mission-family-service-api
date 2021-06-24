package me.missionfamily.web.mission_family_be.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDto {

    @NotNull
    private String userId;
    @NotNull
    private String userPassword;
    @NotEmpty
    private String userName;

}