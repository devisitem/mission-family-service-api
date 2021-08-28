package me.missionfamily.web.mission_family_be.business.family.service;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
                .userPhone("010-0000-0000")
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

}