package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.Builder;
import lombok.Getter;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.MissionDomain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@DiscriminatorColumn(name = "FAMILY_INVITE")
public class InviteMessage extends ServiceRequest{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mf_family_id")
    private Family inviteSenderFamily;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_key")
    private Account inviteTargetAccount;



    @Override
    public ServiceRequest createRequest(MissionDomain messageSender, MissionDomain messageTarget, String title, String content, ServiceProperties typeClass) {
    super.setMessage(title, content, typeClass);
    this.inviteSenderFamily = (Family) messageSender;
    this.inviteTargetAccount = (Account) messageTarget;

    return this;
    }
}
