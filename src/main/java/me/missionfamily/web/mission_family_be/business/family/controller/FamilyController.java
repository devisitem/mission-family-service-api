package me.missionfamily.web.mission_family_be.business.family.controller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.business.family.service.FamilyService;
import me.missionfamily.web.mission_family_be.common.aop.LoginService;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.domain.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @LoginService
    @PostMapping("/create")
    public ResponseEntity<MissionResponse> createNewFamily(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        FamilyModel family = request.getFamily();

        MissionResponse response = familyService.createFamilyGroup(family, request.getAccount().getLoginId());

        return ResponseEntity.ok(response);
    }

    @LoginService
    @GetMapping("/find")
    public ResponseEntity<MissionResponse> findMyFamilies(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        AccountModel account = request.getAccount();

        MissionResponse response = familyService.findFamiliesAsAccount(account);

        return ResponseEntity.ok(response);
    }

    @LoginService
    @PostMapping("/invite-member")
    public ResponseEntity<MissionResponse> sendInviteMessage(@RequestBody @Valid FamilyDxo. Request request) throws ServiceException {

        AccountModel account = request.getAccount();

        return null;
    }


}
