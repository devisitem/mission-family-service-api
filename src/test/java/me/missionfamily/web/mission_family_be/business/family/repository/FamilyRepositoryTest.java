package me.missionfamily.web.mission_family_be.business.family.repository;

import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class FamilyRepositoryTest {


    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Family

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
    public void 멤버_초대메세지_등록() throws Exception {
        /* Given */

        /* When */

        /* Then */

    }

}