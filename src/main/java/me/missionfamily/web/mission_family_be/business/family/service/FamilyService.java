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
import me.missionfamily.web.mission_family_be.domain.Mission;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
     * @return 내가 속한 패밀리 목록조회
     */
    public MissionResponse findFamiliesAsAccount(AccountModel account) throws ServiceException {

        Account foundAccount = accountRepository.findAccountById(account.getLoginId());
        log.info("found 1 account is = [ {} ]", foundAccount);

        List<Family> belongFamily = foundAccount.getBelongFamily();

        if(MissionUtil.isNull(belongFamily)){
            belongFamily = new ArrayList<>();
            log.info("there are no your families. [ {} ]", belongFamily);
        } else {

            log.info("found {} families. It is [ {} ]", belongFamily.size(), belongFamily);
        }
        AtomicInteger index = new AtomicInteger();

        List<FamilyModel>  groupFamilies = belongFamily.stream()
                .map(member ->
                        FamilyModel.builder()
                                .order(index.incrementAndGet())
                                .key(member.getParant().getFamilyId())
                                .familyName(member.getParant().getFamilyName())
                                .joinDate(member.getParant().getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                .deleteYn(member.getParant().getDeleteYn())
                                .build()
                )
                .collect(Collectors.toList());

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .myFamilies(groupFamilies)
                .build();
    }
}
