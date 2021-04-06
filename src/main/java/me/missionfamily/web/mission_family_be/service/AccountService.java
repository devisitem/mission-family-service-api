package me.missionfamily.web.mission_family_be.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

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
     * @param clientMap
     * @return serviceCode
     */
    @Transactional
    public String accountRegister(Map<String,Object> clientMap) {
        String userId = (String) clientMap.get("userId");

        if(dupCheckById(userId)){
            Account account = Account.builder()
                    .userId(userId)
                    .build();
            account.setPassword(encoder.encode((String) clientMap.get("userPassword")));

            accountRepository.save(UserInfo.builder()
                .userName((String) clientMap.get("userName"))
                .account(account)
                .build());

            return "create_success";
        } else {


            return "create_fail";
        }
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
           return true;
        } else {
           return false;
        }

    }
}
