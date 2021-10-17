package me.missionfamily.web.mission_family_be.business.family.controller;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.business.family.model.KickMemberModel;
import me.missionfamily.web.mission_family_be.business.family.service.FamilyService;
import me.missionfamily.web.mission_family_be.common.aop.LoginService;
import me.missionfamily.web.mission_family_be.common.aop.ServiceDescriptions;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Mission;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;


    @LoginService
    @ServiceDescriptions("패밀리 그룹생성")
    @PostMapping("/create")
    public ResponseEntity<MissionResponse> createNewFamily(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        FamilyModel family = request.getFamily();

        MissionResponse response = familyService.createFamilyGroup(family, request.getAccount().getLoginId());

        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("내가 속한 패밀리 찾기")
    @GetMapping("/find")
    public ResponseEntity<MissionResponse> findMyFamilies(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        AccountModel account = request.getAccount();

        MissionResponse response = familyService.findFamiliesAsAccount(account);

        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("우리 패밀리로 멤버 초대하기")
    @PostMapping("/invite-member")
    public ResponseEntity<MissionResponse> sendInviteMessage(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        String memberId = request.getMemberId();
        FamilyModel family = request.getFamily();

        MissionResponse response = familyService.inviteMemberByUserId(memberId, family);

        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("나에게 온 패밀리 초대목록 조회")
    @GetMapping("/invitations")
    public ResponseEntity<MissionResponse> selectInvitationsCameToMe(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {
        String memberId = request.getMemberId();

        MissionResponse response = familyService.collectInvitations(memberId);

        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("초대 승낙 또는 거절")
    @PostMapping("/confirm")
    public ResponseEntity<MissionResponse> confirmInvitations (@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        Long messageKey = request.getConfirm().getMessageKey();
        Boolean opinion = request.getConfirm().getOpinion();
        String loginId = request.getAccount().getLoginId();

        MissionResponse response = familyService.checkInvitation(loginId, messageKey, opinion);


        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("패밀리 내보내기")
    @DeleteMapping("/kick")
    public ResponseEntity<MissionResponse> kickMemberInFamily(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        KickMemberModel kickModel = request.getKickModel();

        MissionResponse response = familyService.kickFamilyMember(kickModel.getFamilyGroupKey(), kickModel.getFamilyMemberKey());

        return ResponseEntity.ok(response);
    }

    @LoginService
    @ServiceDescriptions("멤버 정보 조회")
    @GetMapping("/member")
    public ResponseEntity<MissionResponse> findMemberInformation(@RequestBody @Valid FamilyDxo.Request request) throws ServiceException {

        Long memberKey = request.getFamily().getKey();
        MissionResponse response = familyService.findFamilyMember(memberKey);

        return ResponseEntity.ok(response);
    }


}
