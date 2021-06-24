package me.missionfamily.web.mission_family_be.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.dto.UserDto;
import me.missionfamily.web.mission_family_be.dto.UserRole;
import me.missionfamily.web.mission_family_be.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    public boolean dupCheckById(String checkId){
        Account findAccount = accountRepository.findById(checkId);
        if(null != findAccount){
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param dto
     * @return serviceCode
     */
    @Transactional
    public Account accountRegister(UserDto dto) {

        if(accountRepository.findOneByUserId(dto.getUserId()).orElse(null) != null){
            throw new RuntimeException("this user id is already exist");
        }

        UserRole userRole = UserRole.builder()
                .roleName("USER")
                .build();

        Account account = Account.builder()
                .userId(dto.getUserId())
                .userRole(Collections.singleton(userRole))
                .userPassword(encoder.encode(dto.getUserPassword()))
                .build();

        return accountRepository.save(UserInfo.builder()
            .userName(dto.getUserName())
            .account(account)
            .build());

    }

    /**
     * 로그인 검사
     * @param clientMap
     * @return
     */
    public boolean loginProcess(Map<String,Object> clientMap){
        Account account = accountRepository.findById((String) clientMap.get("id"));
        if(account == null){
            return false;
        }
        if(encoder.matches((String) clientMap.get("password"), account.getUserPassword())){
            System.out.println("Password 일치");
           return true;
        } else {
           return false;
        }

    }

    @Transactional(readOnly = true)
    public Optional<Account> getAccountWithAuthorities(String userId) {
        return accountRepository.findOneByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Optional<Account> getAccountWithRoles(){
        return SecurityUtil.getCurrentUsername().flatMap(accountRepository::findOneByUserId);
    }

}
