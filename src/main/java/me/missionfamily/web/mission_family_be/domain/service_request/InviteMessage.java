package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.Getter;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@DiscriminatorColumn(name = "INVITE")
public class InviteMessage extends ServiceRequest{

    @Id @GeneratedValue
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family messageSender;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_key")
    private Account messageTarget;


    @Override
    public ServiceRequest createRequest(Object messageSender, Object messageTarget, String title, String content, ServiceProperties typeClass) {
    super.setMessage(title, content, typeClass);
    this.messageSender = (Family) messageSender;
    this.messageTarget = (Account) messageTarget;

    return this;
    }
}
