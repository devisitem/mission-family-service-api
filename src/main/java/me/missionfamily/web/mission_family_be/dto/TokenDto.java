package me.missionfamily.web.mission_family_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TokenDto {

    private String token;

}
