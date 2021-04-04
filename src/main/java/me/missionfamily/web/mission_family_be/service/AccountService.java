package me.missionfamily.web.mission_family_be.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public boolean dupCheckById(String checkId){
        System.out.println("AccountService.dupCheckById");
        System.out.println("checkId = " + checkId);
        Account findAccount = accountRepository.findById(checkId);
        if(null != findAccount){
            return false;
        } else {
            return true;
        }
    }
}
