package me.missionfamily.web.mission_family_be.business.family.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.account.repository.AccountRepository;
import me.missionfamily.web.mission_family_be.business.family.dxo.FamilyDxo;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.business.family.model.InvitationModel;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.business.family.repository.FamilyRepository;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
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
        Account leader = accountRepository.findAccountById(loginId, true);

        if(Utils.isNull(leader)){
            log.error("found user can't be null");
            throw new ServiceException(MissionStatus.NO_ACCOUNT_DATA_FOUNDS);
        }
        log.info("The family group has leader, which id by = [{}]", leader.getUserId());

        Family familyGroup = Family.createGroup(groupName, leader);
        familyRepository.save(familyGroup);

        Family member = Family.createFamilyMember(familyGroup, leader);
        familyRepository.save(member);
        member.setDefaultName(Long.valueOf(familyGroup.getFamilyMembers().size()));


        log.info("Created new family group by [{}]",leader.getUserId());

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .family(FamilyModel.builder()
                        .key(familyGroup.getFamilyId())
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

        Account foundAccount = accountRepository.findAccountById(account.getLoginId(), true);
        log.info("found 1 account is = [ {} ]", foundAccount);

        List<Family> belongFamily = foundAccount.getBelongFamily();

        if(Utils.isNull(belongFamily)){
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
                                .key(member.getParent().getFamilyId())
                                .familyName(member.getParent().getFamilyName())
                                .joinDate(member.getParent().getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                .deleteYn(member.getParent().getDeleteYn())
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

    @Transactional
    public MissionResponse inviteMemberByUserId(String memberId, FamilyModel familyModel) throws ServiceException {
        Account foundUser = accountRepository.findAccountById(memberId, true);

        Family senderGroup = familyRepository.findFamilyGroupByKey(familyModel.getKey());

        if(Utils.isNull(senderGroup)){
            log.error("Not found Group. by key is [ {} ]", familyModel.getKey());
            throw new ServiceException(MissionStatus.NOT_FOUND_FAMILIES);
        }

        InviteMessage inviteMessage = new InviteMessage();
        inviteMessage.createRequest(senderGroup, foundUser,senderGroup.getFamilyName()+" 패밀리로 부터 초대","새로운 초대", ServiceProperties.INVITE_MEMBER);

        familyRepository.saveInvite(inviteMessage);

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .memberToBeInvited(foundUser.getUserId())
                .build();
    }

    public MissionResponse collectInvitations(String memberId) throws ServiceException {
        UserInfo userInfo = accountRepository.findUserInfoByUserId(memberId);

        List<InviteMessage> invitations = userInfo.getAccount().getReceivedInvite();
        List<InvitationModel> list = null;
        if(Utils.isNull(invitations) || invitations.size() == 0){

            log.info("There are no invitations.");
        } else {

            list = invitations.stream().filter(req ->  req.getIsConfirmed() == false)
                    .map(req ->
                            InvitationModel.builder()
                                    .key(req.getId())
                                    .title(req.getTitle())
                                    .content(req.getContent())
                                    .sender(req.getInviteSenderFamily().getFamilyName())
                                    .sentTime(req.getSentTime())
                                    .build())
                    .collect(Collectors.toList());

        }

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .invitations(list)
                .build();
    }

    @Transactional
    public MissionResponse checkInvitation(String loginId, Long messageKey, Boolean opinion) throws ServiceException {
        UserInfo userInfo = accountRepository.findUserInfoByUserId(loginId);

        Long groupKey = userInfo.getAccount().checkInvitation(messageKey);

        if(opinion) {

            Family familyGroup = familyRepository.findFamilyGroupByKey(groupKey);

            familyRepository.save(Family.createFamilyMember(familyGroup, userInfo.getAccount()));

            //신규 멤버 환영 푸시이벤트 발행 로직
        }


        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .build();
    }

    @Transactional(rollbackFor = ServiceException.class)
    public MissionResponse kickFamilyMember(final Long groupKey,final Long targetMember) throws ServiceException {

        Family familyGroup = familyRepository.findFamilyGroupByKey(groupKey);
        List<Family> familyMembers = familyGroup.getFamilyMembers();

        Family toBeKickedMember = familyMembers.stream().filter(member ->
                member.getFamilyId().longValue() == targetMember.longValue())
                .findFirst().orElseThrow(() -> new ServiceException(MissionStatus.ALREADY_KICKED_OR_LEAVE));

        boolean isDeleted = toBeKickedMember.deleteMember();

        //멤버 강퇴 푸시이벤트 발행 로직
        if(isDeleted) {

        }

        return FamilyDxo.Response.builder()
                .result(ResponseModel.builder()
                        .code(0)
                        .build())
                .build();
    }

    public MissionResponse findFamilyMember(Long memberKey) throws ServiceException {

        familyRepository.findFamilyMemberByKey(memberKey);

        return null;
    }
}
