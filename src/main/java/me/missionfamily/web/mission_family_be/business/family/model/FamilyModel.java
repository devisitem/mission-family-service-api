package me.missionfamily.web.mission_family_be.business.family.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyModel {

    private String familyName;
}
