package me.missionfamily.web.mission_family_be.business.mission.contoller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.mission.dxo.MissionDxo;
import me.missionfamily.web.mission_family_be.business.mission.service.MissionService;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/create")
    public ResponseEntity<MissionResponse> createMission(@RequestBody @Valid MissionDxo.Request request) throws Exception{

        MissionResponse missionResponse = missionService.createMission(request);

        return ResponseEntity.ok(missionResponse);

    }

}
