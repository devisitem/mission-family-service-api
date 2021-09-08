package me.missionfamily.web.mission_family_be.business.family.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationModel {

    private Long key;
    private String title;
    private String content;
    private String sender;
    private LocalDateTime sentTime;
}
