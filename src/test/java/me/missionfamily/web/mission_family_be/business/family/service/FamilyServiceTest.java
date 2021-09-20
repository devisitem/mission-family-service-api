package me.missionfamily.web.mission_family_be.business.family.service;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class FamilyServiceTest {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void init() throws Exception {
        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("Kimchi-dev")
                        .password("1q2w3e4r")
                        .build())
                .build();
        UserInfo userInfo = UserInfo.builder()
                .userBirth("1994-02-11")
                .account(account)
                .userName("김깍뚝")
                .userPhone("010-000-0000")
                .build();
        accountRepository.save(userInfo);


    }

    @Test
    public void 패밀리_그룹생성_실패_없는유저() throws Exception {
        /* Given */
        FamilyModel familyModel = FamilyModel.builder()
                .familyName("사랑의 테스트 봉사단")
                .build();
        String loginId = "none-user";

        /* When */
        ServiceException thrown = assertThrows(ServiceException.class, () -> familyService.createFamilyGroup(familyModel, loginId));

        /* Then */
        assertEquals(202, thrown.getResultCode());
        assertEquals("등록된 계정이 없거나, 정보가 일치하지 않습니다.", thrown.getMessage());
    }

    @Test
    public void 패밀리_그룹생성_성공() throws Exception {
        /* Given */
        FamilyModel familyModel = FamilyModel.builder()
                .familyName("사랑의 테스트 봉사단")
                .build();
        String loginId = "Kimchi-dev";

        /* When */
        MissionResponse response = familyService.createFamilyGroup(familyModel, loginId);

        /* Then */
        assertEquals(HttpResponseStatus.SUCCESS.getCode(), response.code());

    }

    @Test
    public void 패밀리_멤버_초대() throws Exception {
        /* Given */
        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("kimchidev")
                        .password("1q2w3e4r")
                        .build())
                .build();
        UserInfo userInfo = UserInfo.builder()
                .userBirth("1994-02-11")
                .account(account)
                .userName("김깍뚝")
                .userPhone("010-000-0001")
                .build();
        String target = "Kimchi-dev";

        /* When */
        accountRepository.save(userInfo);
        FamilyDxo.Response familyResponse = (FamilyDxo.Response) familyService.createFamilyGroup(FamilyModel.builder()
                        .familyName("테스트 봉사단.")
                        .build(),
                userInfo.getAccount().getUserId());
        familyService.inviteMemberByUserId(target, FamilyModel.builder().key(familyResponse.getFamily().getKey()).build());
        UserInfo user = accountRepository.findUserInfoByUserId(target);

        /* Then */
        InviteMessage inviteMessage = user.getAccount().getReceivedInvite().get(0);
        assertEquals(ServiceProperties.INVITE_MEMBER, inviteMessage.getTypeClass());


    }

    @Test
    public void 패밀리_멤버_초대실패_유저가_없는경우() throws Exception {
        /* Given */

        String target = "non-user";

        /* When */
        FamilyDxo.Response familyResponse = (FamilyDxo.Response) familyService.createFamilyGroup(FamilyModel.builder()
                        .familyName("테스트 봉사단.")
                        .build(),
                "Kimchi-dev");
        ServiceException thrown = assertThrows(ServiceException.class, () -> familyService.inviteMemberByUserId(target, FamilyModel.builder()
                .key(familyResponse.getFamily().getKey())
                .build()));

        /* Then */
        assertEquals(205, thrown.getResultCode());
        assertEquals("존재하지 않는 유저 입니다.", thrown.getMessage());
    }
    
    @Test
    public void 패밀리_멤버_초대실패_초대한_그룹이_없는경우() throws Exception {
        /* Given */
        String target = "Kimchi-dev";

        /* When */
        ServiceException thrown = assertThrows(ServiceException.class, () -> familyService.inviteMemberByUserId(target, FamilyModel.builder()
                .key(0L)
                .build()));

        /* Then */
        assertEquals(206,thrown.getResultCode());
        assertEquals("패밀리를 찾을수 없습니다.", thrown.getMessage());
    
    }

    @Test
    public void 멤버초대_승낙() throws Exception {
        /* Given */
        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("kimchidev")
                        .password("1q2w3e4r")
                        .build())
                .build();
        UserInfo userInfo = UserInfo.builder()
                .userBirth("1994-02-11")
                .account(account)
                .userName("김깍뚝")
                .userPhone("010-000-0001")
                .build();
        String target = "Kimchi-dev";

        /* When */
        accountRepository.save(userInfo);
        FamilyDxo.Response familyResponse = (FamilyDxo.Response) familyService.createFamilyGroup(FamilyModel.builder()
                        .familyName("테스트 봉사단.")
                        .build(),
                userInfo.getAccount().getUserId());
        familyService.inviteMemberByUserId(target, FamilyModel.builder().key(familyResponse.getFamily().getKey()).build());

        UserInfo user = accountRepository.findUserInfoByUserId(target);
        List<InviteMessage> receivedInvite = user.getAccount().getReceivedInvite();
        InviteMessage message = receivedInvite.stream().findFirst().orElse(null);
        familyService.checkInvitation()
        /* Then */
        assertEquals(1, receivedInvite.size());


    }

}