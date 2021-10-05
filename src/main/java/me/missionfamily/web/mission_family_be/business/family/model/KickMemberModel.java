package me.missionfamily.web.mission_family_be.business.family.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KickMemberModel {

    @JsonProperty("group")
    private Long familyGroupKey;

    @JsonProperty("member")
    private Long familyMemberKey;
}
