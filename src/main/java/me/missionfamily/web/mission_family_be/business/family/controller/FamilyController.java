package me.missionfamily.web.mission_family_be.business.family.controller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.business.family.service.FamilyService;
import me.missionfamily.web.mission_family_be.common.aop.LoginService;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping("/create")
    @LoginService
    public ResponseEntity<MissionResponse> createNewFamily(FamilyDxo.Request request) {

        FamilyModel family = request.getFamily();

        MissionResponse response = familyService.createFamilyGroup(family, request.getAccount().getLoginId());

        return ResponseEntity.ok(response);
    }


}
