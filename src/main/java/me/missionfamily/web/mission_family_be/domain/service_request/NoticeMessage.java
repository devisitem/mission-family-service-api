package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.Getter;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.MissionDomain;
import org.springframework.util.Assert;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@DiscriminatorColumn(name = "NOTICE")
public class NoticeMessage extends ServiceRequest{


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family noticeSenderFamily;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family noticeTargetFamily;

    /**
     * 메세지를 보내는 패밀리는 멤버이어만하고,
     * 메세지를 받는 패밀리는 멤버(개별 공지)또는 그룹(패밀리 내 전체공지)이 될 수 있다.
     *
     */


    @Override
    ServiceRequest createRequest(MissionDomain messageSender, MissionDomain messageTarget, String title, String content, ServiceProperties typeClass) {
        super.setMessage(title, content, typeClass);
        this.noticeSenderFamily = (Family) messageSender;
        this.noticeTargetFamily = (Family) messageTarget;
        return this;
    }

}
