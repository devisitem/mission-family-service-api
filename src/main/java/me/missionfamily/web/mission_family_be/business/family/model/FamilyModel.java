package me.missionfamily.web.mission_family_be.business.family.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyModel {

    @NotNull
    @JsonProperty("name")
    private String familyName;
}
