package me.missionfamily.web.mission_family_be.business.family.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final AccountRepository accountRepository;


    /**
     * /api/families/create 패밀리 그룹생성
     * @param family
     * @param loginId
     * @return
     */
    @Transactional
    public MissionResponse createFamilyGroup(FamilyModel family, String loginId) {

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
}
