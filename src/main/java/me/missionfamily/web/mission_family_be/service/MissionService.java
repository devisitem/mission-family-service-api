package me.missionfamily.web.mission_family_be.service;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.Mission;
import me.missionfamily.web.mission_family_be.repository.MissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public void createMission(Mission mission){
        missionRepository.saveMission(mission);

    }

}
