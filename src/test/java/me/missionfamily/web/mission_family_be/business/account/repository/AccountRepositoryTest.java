package me.missionfamily.web.mission_family_be.business.account.repository;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    UserInfo testUser;

    @BeforeEach
    @DisplayName("테스트 계정 생성")
    public void init(){

        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("Kimchi-dev")
                        .password("1q2w3e4r")
                        .build())
                .build();

        this.testUser = UserInfo.builder()
                .userBirth("1994-02-11")
                .account(account)
                .userName("가물치")
                .userPhone("010-0000-0000")
                .build();
    }

    @Test
    @Transactional
    public void 유저_저장후_아이디로_계정조회() throws Exception {
        //given
        UserInfo userInfo = testUser;

        //when
        accountRepository.save(userInfo);
        Account account = accountRepository.findAccountById("Kimchi-dev");

        //then
        assertEquals("Kimchi-dev",account.getUserId());
        assertThrows(NullPointerException.class, () -> account.getUserInfo().getAccount());

    }

    @Test
    @Transactional
    public void 유저_저장후_아이디로_회원정보조회() throws Exception {
        //given
        UserInfo userInfo = testUser;
        UserInfo secondUserInfo = testUser;
        secondUserInfo.getAccount().setPassword("wqeqw21312");

        //when
        accountRepository.save(userInfo);
        UserInfo user = accountRepository.findUserInfoByUserId("Kimchi-dev");

        //then
        assertEquals("Kimchi-dev",user.getAccount().getUserId());
        assertEquals("010-0000-0000", userInfo.getUserPhone());

    }


}