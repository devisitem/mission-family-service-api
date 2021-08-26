package me.missionfamily.web.mission_family_be.business.family.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.business.family.repository.FamilyRepository;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final AccountRepository accountRepository;


    /**
     * /api/families/create
     * @param family :
     *    family: {
     *         name: "Some family name"
     *    }
     * @param loginId : 로그인아이디
     * @return 패밀리 그룹생성
     */
    @Transactional
    public MissionResponse createFamilyGroup(FamilyModel family, String loginId) throws ServiceException {

        String groupName = family.getFamilyName();
        Account leader = accountRepository.findAccountById(loginId);

        if(MissionUtil.isNull(leader)){
            log.error("found user can't be null");
            throw new ServiceException(HttpResponseStatus.NO_ACCOUNT_DATA_FOUNDS);
        }
        log.info("The family group has leader, which id by = [{}]", leader.getUserId());

        Family familyGroup = Family.createGroup(groupName, leader);
        familyRepository.save(familyGroup);

        Family member = Family.createFamilyMember(familyGroup, leader);
        familyRepository.save(member);

        log.info("Created new family group by [{}]",leader.getUserId());

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .build();
    }

    /**
     * /api/families/find
     * @param account : {
     *                "login_id": "Login Id",
     *                "mission_signature": "21mNlsSYla..."
     * }
     * @return 내가 속한 패밀리 그룹 전체
     */
    public MissionResponse findFamiliesAsAccount(AccountModel account) throws ServiceException {

        Account foundAccount = accountRepository.findAccountById(account.getLoginId());

        List<Family> myFamilies = foundAccount.getMyFamilies();
        log.info("found {} families. It is [ {} ]", myFamilies.size(), myFamilies);

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .myFamilies(myFamilies)
                .build();
    }
}
