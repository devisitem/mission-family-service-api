package me.missionfamily.web.mission_family_be.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServerResponse {
    private String serviceCode;
    private String serviceMsg;

    public ServerResponse(String serviceCode, String serviceMsg){
        this.serviceCode = serviceCode;
        this.serviceMsg = serviceMsg;
    }
}
