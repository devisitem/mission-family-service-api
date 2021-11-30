package me.missionfamily.web.mission_family_be.business.account.service;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    private UserInfo testInfo;

    @BeforeAll
    public AccountServiceTest 피클이(){
        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("Pickle-dev")
                        .password("1q2w3e4r")
                        .build())
                .build();

        UserInfo userInfo = UserInfo.builder()
                .userBirth("1994-02-11")
                .account(account)
                .userName("pickle")
                .userPhone("010-0000-0000")
                .build();

        this.testInfo = userInfo;
        return this;
    }

    @Test
    public void 아이디_중복체크_성공하고() throws Exception {
        /* Given */
        AccountDxo.Request dto = AccountDxo.Request.builder()
                .userId(this.testInfo.getAccount().getUserId())
                .build();

        /* When */
        MissionResponse response = assertDoesNotThrow(() -> accountService.dupCheckById(dto), "");

        /* Then*/
        assertEquals(response.code(), HttpResponseStatus.SUCCESS.getCode());

    }

    @Test
    public void 아이디_중복체크_실패() throws Exception {
        /* Given */
        AccountDxo.Request dto = AccountDxo.Request.builder()
                .userId(this.testInfo.getAccount().getUserId())
                .build();

        /* When */
        accountRepository.save(this.testInfo);
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.dupCheckById(dto));

        /* Then*/
        assertEquals(HttpResponseStatus.USER_ID_DUPLICATED.getCode(), thrown.getResultCode());

    }

    @Test
    public void 계정_회원가입() throws Exception {
        //given
        AccountDxo.Request request = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e4r")
                .userName("김치전")
                .build();

        //when
        AccountDxo.Response response = (AccountDxo.Response) accountService.registerForAccount(request);

        //then
        assertEquals(0, response.code());
    }

    @Test
    public void 계정_로그인_아이디미존재() throws Exception {
        //given
        AccountDxo.Request signUp = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e4r")
                .userName("김치전")
                .build();

        AccountDxo.Request signIn = AccountDxo.Request.builder()
                .userId("Kimchi-dev1")
                .password("1q2w3e4r")
                .userName("김치전")
                .build();



        //when
        AccountDxo.Response response = (AccountDxo.Response) accountService.registerForAccount(signUp);
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.signInForAccount(signIn));

        //then
        assertEquals(202, thrown.getResultCode());
        assertEquals("등록된 계정이 없거나, 정보가 일치하지 않습니다.", thrown.getMessage());

    }

    @Test
    public void 계정_로그인_비밀번호_불일치() throws Exception {
        //given
        AccountDxo.Request signUp = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e4r")
                .userName("김치전")
                .build();

        AccountDxo.Request signIn = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e")
                .build();



        //when
        AccountDxo.Response response = (AccountDxo.Response) accountService.registerForAccount(signUp);
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.signInForAccount(signIn));

        //then
        assertEquals(202, thrown.getResultCode());
        assertEquals("등록된 계정이 없거나, 정보가 일치하지 않습니다.", thrown.getMessage());

    }
    
    @Test
    public void 일반_계정_로그인_성공() throws Exception {
        //given
        AccountDxo.Request signUp = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e4r")
                .userName("김치전")
                .build();

        AccountDxo.Request signIn = AccountDxo.Request.builder()
                .userId("Kimchi-dev")
                .password("1q2w3e4r")
                .build();


        //when
        AccountDxo.Response response = (AccountDxo.Response) accountService.registerForAccount(signUp);
        MissionResponse signInResponse = accountService.signInForAccount(signIn);

        //then
        assertEquals(HttpResponseStatus.SUCCESS.getCode(), signInResponse.code());
    
    }
    

}