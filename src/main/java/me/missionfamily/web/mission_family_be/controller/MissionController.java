package me.missionfamily.web.mission_family_be.controller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.ServerResponse;
import me.missionfamily.web.mission_family_be.dto.MissionDto;
import me.missionfamily.web.mission_family_be.service.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity createMission(@RequestBody @Valid MissionDto dto, Errors erros) throws Exception{
        if(erros.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        missionService.createMission(dto);



        return new ResponseEntity("미션이 생성 되었습니다.",HttpStatus.OK);
    }
}
