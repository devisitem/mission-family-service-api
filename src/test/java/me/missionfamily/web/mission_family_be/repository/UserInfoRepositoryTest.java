package me.missionfamily.web.mission_family_be.repository;

import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Rollback(value = false)
    public void 전체유저_조회() throws Exception {


        //given
        UserInfo userInfo = create_temp_user();
        UserInfo userInfo2 = create_temp_user2();

        //when
        accountRepository.save(userInfo);
        accountRepository.save(userInfo2);

        //when
        List<UserInfo> allUsers = userInfoRepository.findAll();

        //then
        Assertions.assertThat(allUsers.size()).isEqualTo(2);
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