package me.missionfamily.web.mission_family_be.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionErrorResponse implements MissionResponse {

    @JsonProperty("error")
    private ExceptionModel exception;

    @Override
    public int getCode() {
        return exception.getErrorCode();
    }
}
