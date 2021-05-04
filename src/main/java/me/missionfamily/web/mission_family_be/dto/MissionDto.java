package me.missionfamily.web.mission_family_be.dto;

import com.mysema.commons.lang.Assert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
public class MissionDto {
    private String name;
    @NotEmpty
    private String type;

}
