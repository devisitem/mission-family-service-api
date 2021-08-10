package me.missionfamily.web.mission_family_be.common.data_transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {

    @Min(0)
    @JsonProperty("code")
    int resultCode;

}
