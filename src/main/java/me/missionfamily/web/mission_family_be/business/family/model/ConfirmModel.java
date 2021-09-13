package me.missionfamily.web.mission_family_be.business.family.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmModel {

    @Min(0)
    @JsonProperty("message_key")
    private Long messageKey;

    @NotNull
    private Boolean opinion;

}
