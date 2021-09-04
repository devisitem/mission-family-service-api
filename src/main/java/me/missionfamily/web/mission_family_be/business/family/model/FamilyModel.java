package me.missionfamily.web.mission_family_be.business.family.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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

    @JsonInclude(Include.NON_NULL)
    private int order;

    @JsonInclude(Include.NON_NULL)
    private Long key;

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("name")
    private String familyName;

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("join_date")
    private String joinDate;

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("delete_yn")
    private String deleteYn;



}
