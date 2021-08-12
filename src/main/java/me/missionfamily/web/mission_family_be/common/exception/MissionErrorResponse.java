package me.missionfamily.web.mission_family_be.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.data_transfer.Response;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionErrorResponse implements Response {

    private ExceptionModel exception;

}
