package me.missionfamily.web.mission_family_be.business.family.controller;

import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/families")
public class FamilyController {


    @PostMapping("/create")
    public ResponseEntity<MissionResponse> createNewFamily(FamilyDxo.Request request) {

        return ResponseEntity.ok().body();
    }
}
