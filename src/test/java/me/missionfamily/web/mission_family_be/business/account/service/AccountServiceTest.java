package me.missionfamily.web.mission_family_be.business.account.service;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    public void init(){
        Account account = Account.builder()
                .dxo(AccountDxo.Request.builder()
                        .userId("KkackDdugy-dev")
                        .password("1q2w3e4r")
                        .build())
                .build();

        accountRepository.save(UserInfo.builder()
                                    .userBirth("1994-02-11")
                                    .account(account)
                                    .userName("김깍뚝")
                                    .userPhone("010-0000-0000")
                                    .build());
    }

    @Test
    public void 중복된_아이디가_존재() throws Exception {
        //given
        String targetID = "KkackDdugy-dev";

        //when
        ServiceException thrown = assertThrows(ServiceException.class, () ->
                accountService.dupCheckById(targetID));

        //then
        assertEquals(201,thrown.getResultCode());
        assertEquals("이미 존재하는 아이디입니다.",thrown.getMessage());
    }

    @Test
    public void 중복된_아이디가_미존재() throws Exception {
        //given
        String targetID = "Kimchi-dev";

        //when
        AccountDxo.Response response = accountService.dupCheckById(targetID);

        //then
        assertEquals(0, response.getResultCode());
        assertEquals("KkackDdugy-dev", response.getCheckedId());

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
        AccountDxo.Response response = accountService.registerForAccount(request);

        //then
        assertEquals(0, response.getResultCode());
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
        AccountDxo.Response response = accountService.registerForAccount(signUp);
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
        AccountDxo.Response response = accountService.registerForAccount(signUp);
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.signInForAccount(signIn));

        //then
        assertEquals(202, thrown.getResultCode());
        assertEquals("등록된 계정이 없거나, 정보가 일치하지 않습니다.", thrown.getMessage());

    }

}