package me.missionfamily.web.mission_family_be.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionModel {

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("server_message")
    private String serverMessage;
}
