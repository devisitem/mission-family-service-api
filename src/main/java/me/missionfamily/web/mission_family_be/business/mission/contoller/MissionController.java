package me.missionfamily.web.mission_family_be.business.mission.contoller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.mission.model.MissionModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.business.mission.dxo.MissionDxo;
import me.missionfamily.web.mission_family_be.business.mission.service.MissionService;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.domain.Mission;
import me.missionfamily.web.mission_family_be.proxy.ExtendableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final ExtendableService<Mission> missionPService;

    @PostMapping("/create")
    public ResponseEntity<MissionResponse> createMission(@RequestBody @Valid MissionDxo.Request request) throws Exception{

        MissionResponse missionResponse = missionService.createMission(request);

        return ResponseEntity.ok().body(missionResponse);

    }

    @GetMapping("/proxy")
    public ResponseEntity<MissionResponse> proxiedSave(@RequestBody String name) throws ServiceException {

        Mission mission = Mission.createMission(MissionModel.builder().build());
        missionPService.saveOne(mission);



        return ResponseEntity.ok(MissionDxo.Response.builder()
                .result(ResponseModel.builder()
                        .resultCode(0)
                        .build())
                .build());
    }
}
