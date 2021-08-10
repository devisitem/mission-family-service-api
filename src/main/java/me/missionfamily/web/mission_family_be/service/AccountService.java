package me.missionfamily.web.mission_family_be.service;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Mission;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.business.account.dxo.UserDxo;
import me.missionfamily.web.mission_family_be.dto.UserRole;
import me.missionfamily.web.mission_family_be.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    public AccountDxo.Response dupCheckById(String checkId){

        Account findAccount = accountRepository.findAccountById(checkId);

        if(MissionUtil.isNotNull(findAccount)) {

//            throw new RuntimeException("이미 존재하는 아이디입니다.");
            System.out.println("이미 존재하는 아이디입니다.");
        }
        System.out.println("중복된 아이디 없음");

        return AccountDxo.Response.builder()
                .result(ResponseModel.builder()
                        .resultCode(0)
                        .build())
                .build();
    }

    /**
     *
     * @param accountDxo
     * @return serviceCode
     */
    @Transactional
    public AccountDxo.Response registerForAccount (final AccountDxo.Request accountDxo) throws Exception {

        Account foundAccount = accountRepository.findAccountById(accountDxo.getUserId());

        if(MissionUtil.isNotNull(foundAccount)) {
            System.out.println("이미 존재하는 아이디입니다.");
        }


/*     if(accountRepository.findOneByUserId(dto.getUserId()).orElse(null) != null){
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
            .build());*/
        return null;
    }

    /**
     * 로그인 검사
     * @param clientMap
     * @return
     */
    public boolean loginProcess(Map<String,Object> clientMap){
        Account account = accountRepository.findAccountById((String) clientMap.get("id"));
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
