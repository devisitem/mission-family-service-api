package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.Getter;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Family;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@DiscriminatorColumn(name = "NOTICE")
public class NoticeMessage extends ServiceRequest{


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family messageSender;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family messageTarget;

    /**
     * 메세지를 보내는 패밀리는 멤버이어만하고,
     * 메세지를 받는 패밀리(패밀리 내 전체공지)는 멤버(개별 공지)또는 그룹이 될 수 있다.
     *
     */


    @Override
    ServiceRequest createRequest(Family messageSender, Family messageTarget, String title, String message, ServiceProperties typeClass) {
        super.setMessage(title, message, typeClass);
        this.messageSender = messageSender;
        this.messageTarget = messageTarget;
        return this;
    }

}
