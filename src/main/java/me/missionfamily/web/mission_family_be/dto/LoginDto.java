package me.missionfamily.web.mission_family_be.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class LoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
