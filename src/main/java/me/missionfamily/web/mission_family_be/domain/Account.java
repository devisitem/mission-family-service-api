package me.missionfamily.web.mission_family_be.domain;

import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@Table(name = "mf_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements MissionDomain{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_key")
    private Long id;

    @Column(name = "mf_user_id",length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "mf_user_password",length = 800)
    private String userPassword;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_signup_date")
    private LocalDateTime signUpDate;

    @OneToOne(mappedBy = "account",fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Family> myFamilies = new ArrayList<>();

    @OneToMany(mappedBy = "familyKey",cascade = CascadeType.ALL)
    private List<Family> belongFamily = new ArrayList<>();

    @OneToMany(mappedBy = "inviteTargetAccount", cascade = CascadeType.ALL)
    private List<InviteMessage> receivedInvite = new ArrayList<>();

    private boolean activated;

    public void setPassword(String password){
        this.userPassword = password;
    }

    @Builder
    public Account(AccountDxo.Request dxo){
        this.userId = dxo.getUserId();
        this.userPassword = dxo.getPassword();
        this.deleteYn = "N";
        this.activated = true;
        this.signUpDate = LocalDateTime.now();
    }

    //==== Association Mapping ====//

    public void addUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
        userInfo.setAccount(this);
    }


    //==== Business Logic ====//

    /**
     *  패밀리 초대 수락
     * @param messageKey
     * @return
     */
    public Long checkInvitation(final Long messageKey) {
        InviteMessage inviteMessage = this.receivedInvite.stream().filter(msg -> ! msg.getIsConfirmed())
                .filter(msg -> msg.getId().equals(messageKey))
                .findFirst().orElse(null);

        if(Utils.isNull(inviteMessage)) {
            throw new ServiceException(MissionStatus.NON_EXIST_MESSAGE);
        }

        inviteMessage.confirmMessage();

        return inviteMessage.getInviteSenderFamily().getFamilyId();
    }




}
