package me.missionfamily.web.mission_family_be.common.transactional.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
    String title;
    String content;
    String group;
    String topic;
    LocalDate createTime;
}
