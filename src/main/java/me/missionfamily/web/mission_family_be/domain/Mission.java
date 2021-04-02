package me.missionfamily.web.mission_family_be.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "mf_mission")
public class Mission {

    @Id @GeneratedValue
    @Column(name = "mf_mission_id")
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_parent_mission")
    private  Mission parent;

    @Column(name = "mf_mission_name",length = 200)
    private String missionName;

    @Column(name = "mf_title",length = 300)
    private String title;

    @Column(name = "mf_content",length = 1000)
    private String content;

    @JoinColumn(name = "mf_belong_family")
    @ManyToOne(fetch = FetchType.LAZY)
    private Family belongFamily;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_creater")
    private Family creater;

    @Enumerated(EnumType.STRING)
    @Column(name = "mf_mission_status",length = 30)
    private MissionStatus missionStatus;

    @Column(name = "mf_start_date")
    private Date startDate;

    @Column(name = "mf_end_date")
    private Date endDate;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<ClearProof> proofs = new ArrayList<>();

}
