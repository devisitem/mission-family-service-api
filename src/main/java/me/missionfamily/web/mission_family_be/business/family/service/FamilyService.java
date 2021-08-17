package me.missionfamily.web.mission_family_be.business.family.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.business.family.repository.FamilyRepository;
import me.missionfamily.web.mission_family_be.domain.Family;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    public MissionResponse createFamilyGroup(FamilyModel family) {



    }
}
