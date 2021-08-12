package me.missionfamily.web.mission_family_be.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final PasswordEncoder encoder;
    private final PasswordEncoder passwordEncoder;

    public AccountDxo.Response dupCheckById(String checkId){
        Account foundAccount = accountRepo.findAccountById(checkId);
        log.info("optionalAccount = [{}]", foundAccount);

        if(MissionUtil.isNotNull(foundAccount)) {

//            throw new RuntimeException("이미 존재하는 아이디입니다.");
            System.out.println("이미 존재하는 아이디입니다.");
        }
        System.out.println("중복된 아이디 없음");

        return AccountDxo.Response.builder()
                .result(ResponseModel.builder()
                        .resultCode(0)
                        .build())
                .checkedId(checkId)
                .build();
    }

    /**
     *
     * @param accountDxo
     * @return serviceCode
     */
    @Transactional
    public AccountDxo.Response registerForAccount (AccountDxo.Request accountDxo) throws Exception {
        Account foundAccount = accountRepo.findAccountById(accountDxo.getUserId());

        log.info("optionalAccount = [{}]", foundAccount);

        if(MissionUtil.isNotNull(foundAccount)) {
            log.info("this id is already exist. id = [{}]",accountDxo.getUserId());
        }

        accountDxo.setPassword(passwordEncoder.encode(accountDxo.getPassword()));

        foundAccount = Account.builder()
                .dxo(accountDxo)
                .build();

        UserInfo newerUser = UserInfo.builder()
                .userName(accountDxo.getUserName())
                .userBirth(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .account(foundAccount)
                .build();


        Long newerId = accountRepo.save(newerUser);

        log.info("The saved Id is [{}]", newerId);
        log.info("created new User for id is = [{}]", foundAccount.getUserId());

        return AccountDxo.Response.builder()
                .result(ResponseModel.builder()
                        .resultCode(0)
                        .build())
                .build();
    }

    /*
     * 로그인 검사
     * @param clientMap
     * @return
     */
//    public boolean loginProcess(Map<String,Object> clientMap){
//        List<Account> optionalAccount = accountRepo.findAccountById((String) clientMap.get("id"));
//
//        log.info("optionalAccount = [{}]", optionalAccount);
//
//        Account account = optionalAccount.get();
//        if(account == null){
//            return false;
//        }
//        if(encoder.matches((String) clientMap.get("password"), account.getUserPassword())){
//            System.out.println("Password 일치");
//           return true;
//        } else {
//           return false;
//        }
//
//    }
//
//    public Optional<Account> getAccountWithAuthorities(String userId) {
//        return accountRepo.findOneByUserId(userId);
//    }
//
//    public Optional<Account> getAccountWithRoles(){
//        return SecurityUtil.getCurrentUsername().flatMap(accountRepo::findOneByUserId);
//    }

}
