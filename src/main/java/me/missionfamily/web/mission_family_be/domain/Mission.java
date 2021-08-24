package me.missionfamily.web.mission_family_be.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.business.mission.dxo.MissionDxo;
import me.missionfamily.web.mission_family_be.business.mission.model.MissionModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mf_mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    @Id @GeneratedValue
    @Column(name = "mf_mission_id")
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_parent_mission")
    private  Mission parent;

    @Column(name = "mf_type", length = 50)
    private String missionType;

    @Column(name = "mf_mission_name",length = 200)
    private String missionName;

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
    private LocalDateTime startDate;

    @Column(name = "mf_end_date")
    private LocalDateTime endDate;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<ClearProof> proofs = new ArrayList<>();


    public static Mission createMission(MissionModel model){

        //미션생성하는 유저 정보 필요

        Mission mission = new Mission();

        mission.missionName = model.getMissionTitle();
        mission.content = model.getMissionContent();
        mission.startDate = model.getMissionStartDate() != null ? LocalDateTime.parse(model.getMissionStartDate(), DateTimeFormatter.ISO_DATE) : null;
        mission.endDate = model.getMissionEndDate() != null ? LocalDateTime.parse(model.getMissionEndDate(), DateTimeFormatter.ISO_DATE) : null;
        mission.missionType = model.getMissionType();

        mission.createDate = LocalDateTime.now();

        return mission;
    }


}
