package me.missionfamily.web.mission_family_be.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mf_family")
public class Family {

    @Id @GeneratedValue
    @Column(name = "mf_family_id")
    private Long familyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_parent_family")
    private Family parant;

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

    @OneToMany(mappedBy = "creater",cascade = CascadeType.ALL)
    private List<Mission> myMissions = new ArrayList<>();

    public static Family createGroup(String familyName, Account leader) {
        Family newerGroup = new Family();
        newerGroup.account = leader;
        newerGroup.role = "GROUP";
        newerGroup.parant = null;


    }
}
