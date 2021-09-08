package me.missionfamily.web.mission_family_be.business.family.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmModel {

    @Min(0)
    private Long familyKey;

    @NotNull
    private Boolean opinion;

}
