package me.missionfamily.web.mission_family_be.domain;

import io.jsonwebtoken.lang.Assert;
import lombok.Getter;
import lombok.ToString;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.NoticeMessage;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mf_family")
public class Family implements MissionDomain{

    @Id @GeneratedValue
    @Column(name = "mf_family_id")
    private Long familyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_parent_family")
    private Family parent;

    @Column(name = "mf_role",length = 50)
    private String role;

    @Column(name = "mf_family_name",length = 200)
    private String familyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_family_leader")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_key")
    private Account familyKey;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_join_date")
    private LocalDateTime joinDate;

    @OneToMany(mappedBy = "creater", cascade = CascadeType.ALL)
    private List<ClearProof> clearProofs = new ArrayList<>();

    @OneToMany(mappedBy = "belongFamily",cascade = CascadeType.ALL)
    private List<Mission> belongMissions = new ArrayList<>();

    @OneToMany(mappedBy = "creator",cascade = CascadeType.ALL)
    private List<Mission> myMissions = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Family> familyMembers = new ArrayList<>();

    @OneToMany(mappedBy = "noticeSenderFamily", cascade = CascadeType.ALL)
    private List<NoticeMessage> receivedNotice = new ArrayList<>();

    @OneToMany(mappedBy = "noticeTargetFamily", cascade = CascadeType.ALL)
    private List<NoticeMessage> sentNotice = new ArrayList<>();

    @OneToMany(mappedBy = "inviteSenderFamily", cascade = CascadeType.ALL)
    private List<InviteMessage> sentInvite = new ArrayList<>();


    /**
     * 패밀리 "그룹" 생성
     * @param familyName 패밀리명
     * @param leader 생성자 (리더)
     * @return
     */
    public static Family createGroup(String familyName, Account leader) {
        Family newerGroup = new Family();
        newerGroup.account = leader;
        newerGroup.familyName = familyName;
        newerGroup.role = "GROUP";
        newerGroup.parent = null;
        newerGroup.familyKey = null;
        newerGroup.deleteYn = "N";
        newerGroup.joinDate = LocalDateTime.now();

        return newerGroup;
    }

    /**
     * 패밀리 "멤버" 생성
     * @param group 소속될 그룹
     * @param member 연결 계정
     * @return
     */
    public static Family createFamilyMember(Family group, Account member) {
        Family newerMember = new Family();
        newerMember.familyKey = member;
        newerMember.parent = group;
        newerMember.role = "MEMBER";
        newerMember.deleteYn = "N";
        newerMember.joinDate = LocalDateTime.now();

        return newerMember;
    }


    public void setDefaultName(Long index) {
        this.familyName = "패밀리" + index;
    }
}
