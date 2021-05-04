package me.missionfamily.web.mission_family_be.service;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.dto.MissionDto;
import me.missionfamily.web.mission_family_be.repository.MissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public void createMission(MissionDto dto){



    }

}
