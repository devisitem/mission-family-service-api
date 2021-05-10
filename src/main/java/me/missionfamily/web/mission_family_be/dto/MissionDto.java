package me.missionfamily.web.mission_family_be.dto;

import com.mysema.commons.lang.Assert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.missionfamily.web.mission_family_be.domain.Mission;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

// import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
public class MissionDto {

    private String missionTitle;
    private String missionContent;
    private String missionStartDate;
    private String missionEndDate;

    @NotEmpty(message = "The Mission Type is never be null")
    private String missionType;

    public Mission getMissionConvertor(){
        return Mission.createMission(this);
    }
}
