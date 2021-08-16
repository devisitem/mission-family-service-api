package me.missionfamily.web.mission_family_be.business.mission.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionModel {

    @JsonProperty("mission_title")
    private String missionTitle;

    @JsonProperty("mission_content")
    private String missionContent;

    @JsonProperty("mission_start_date")
    private String missionStartDate;

    @JsonProperty("mission_end_date")
    private String missionEndDate;

    @NotEmpty(message = "The Mission Type is never be null")
    @JsonProperty("mission_type")
    private String missionType;
}
