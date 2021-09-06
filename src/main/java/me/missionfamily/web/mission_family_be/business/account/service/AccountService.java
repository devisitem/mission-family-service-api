package me.missionfamily.web.mission_family_be.business.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final PasswordEncoder passwordEncoder;

    public MissionResponse dupCheckById(String checkId) throws ServiceException{
        Account foundAccount = accountRepo.findAccountById(checkId);
        log.info("optionalAccount = [{}]", foundAccount);

        if(MissionUtil.isNotNull(foundAccount)) {
            log.error("This Login ID is Already registered. ID = [ {} ]", checkId);
            throw new ServiceException(HttpResponseStatus.USER_ID_DUPLICATE);
        }

        log.info("there is no this identification. usable identification = [{}]", checkId);

        return AccountDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
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
    public MissionResponse registerForAccount (AccountDxo.Request accountDxo) throws ServiceException {
        Account foundAccount = accountRepo.findAccountById(accountDxo.getUserId());


        if(MissionUtil.isNotNull(foundAccount)) {
            log.info("this id is already exist. id = [{}]",accountDxo.getUserId());
            throw new ServiceException(HttpResponseStatus.USER_ID_DUPLICATE);
        }
        log.info("there is no id founds, which is duplicated. by = [{}]", accountDxo.getUserId());
        log.info("encoding this password = [{}]",accountDxo.getPassword());
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
                        .code(0)
                        .build())
                .build();
    }

    @Transactional
    public MissionResponse signInForAccount(AccountDxo.Request accountDxo) throws ServiceException {

        UserInfo foundUser = accountRepo.findUserInfoByUserId(accountDxo.getUserId());
        if(MissionUtil.isNull(foundUser)){

            log.info("no account data founds ,which is be registered. id = [{}]",accountDxo.getUserId());
            throw new ServiceException(HttpResponseStatus.NO_ACCOUNT_DATA_FOUNDS);
        }

        if( ! passwordEncoder.matches(accountDxo.getPassword(), foundUser.getAccount().getUserPassword())){
            log.info("found 1 account and proceeded verification but It didn't matched with password.");
            throw new ServiceException(HttpResponseStatus.NO_ACCOUNT_DATA_FOUNDS);
        }

        String missionKey = foundUser.generateAndRefreshAuthKey();

        log.info("create auth key for sign-in this service. auth-key = [{}]",missionKey);



        return AccountDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .account(AccountModel.builder()
                        .loginId(foundUser.getAccount().getUserId())
                        .missionSignature(missionKey)
                        .build())
                .build();
    }

    /*
     * 유저정보 인증
     * @param reuqest
     * @return
     */
    public void authenticationProcess(AccountDxo.Request request) throws ServiceException {

        AccountModel account = request.getAccount();
        UserInfo fountUserInfo = accountRepo.findUserInfoByUserId(account.getLoginId());

        String signature = account.getMissionSignature();

        if(MissionUtil.isNotEmptyAndNull(signature)){
            log.error("the auth key is null in request");
            throw new ServiceException(HttpResponseStatus.AUTHKEY_MUST_BE_NON_NULL);
        }

        if( ! signature.equals(fountUserInfo.getAuthKey())){
            log.error("failed authenticate to this process.");
            throw new ServiceException(HttpResponseStatus.FAILED_AUTHENTICATE_PROCESS);
        }

    }

}
