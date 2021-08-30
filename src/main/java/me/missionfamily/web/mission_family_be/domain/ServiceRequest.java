package me.missionfamily.web.mission_family_be.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceRequest {

    @Id @GeneratedValue
    private Long id;
    private String message;


}
