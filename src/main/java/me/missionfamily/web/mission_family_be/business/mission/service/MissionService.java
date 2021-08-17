package me.missionfamily.web.mission_family_be.business.mission.service;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.mission.dxo.MissionDxo;
import me.missionfamily.web.mission_family_be.business.mission.model.MissionModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.domain.Mission;
import me.missionfamily.web.mission_family_be.business.mission.repository.MissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResponse createMission(MissionDxo.Request missionDxo){

        MissionModel model = MissionModel.builder()
                .missionTitle(missionDxo.getMission().getMissionTitle())
                .missionContent(missionDxo.getMission().getMissionContent())
                .missionStartDate(missionDxo.getMission().getMissionStartDate())
                .missionEndDate(missionDxo.getMission().getMissionEndDate())
                .missionType(missionDxo.getMission().getMissionType())
                .build();

        Mission newerMission = Mission.createMission(model);

        missionRepository.saveMission(newerMission);

        return MissionDxo.Response.builder()
                .result(ResponseModel.builder()
                        .resultCode(0)
                        .build())
                .build();
    }

}
