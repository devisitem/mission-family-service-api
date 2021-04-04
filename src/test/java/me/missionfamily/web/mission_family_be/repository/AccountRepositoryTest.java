package me.missionfamily.web.mission_family_be.repository;

import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void 유저_회원가입 (){
        //given
        UserInfo userInfo = create_temp_user();
        UserInfo userInfo2 = create_temp_user2();

        //when
        accountRepository.save(userInfo);
        accountRepository.save(userInfo2);

        System.out.println("userInfo = " + userInfo);
        System.out.println("userInfo2 = " + userInfo2);

        //then
        Assertions.assertThat(userInfo.getAccount().getUserId()).isEqualTo("tgjeon");
        Assertions.assertThat(userInfo2.getAccount().getUserId()).isEqualTo("happyoonj7");

    }

    @Test
    public void 아이디_중복확인() throws Exception {
        //given
        UserInfo userInfo = create_temp_user();
        accountRepository.save(userInfo);
        //when
        Account findAccount = accountRepository.findById("tgjeon");

        //then
        Assertions.assertThat(userInfo.getAccount().getUserId()).isEqualTo(findAccount.getUserId());
    }

    public UserInfo create_temp_user(){




        Account account = Account.builder()
                .userId("tgjeon")
                .build();

        UserInfo userInfo = UserInfo.builder()
                .userName("전태구")
                .userPhone("010-4138-2383")
                .userBirth("1994-09-23")
                .account(account)
                .build();

        return userInfo;
    }
    public UserInfo create_temp_user2(){


        Account account = Account.builder()
                .userId("happyoonj7")
                .build();

        UserInfo userInfo = UserInfo.builder()
                .userName("최융정")
                .userPhone("010-7529-0117")
                .userBirth("1992-01-17")
                .account(account)
                .build();



        return userInfo;
    }


}