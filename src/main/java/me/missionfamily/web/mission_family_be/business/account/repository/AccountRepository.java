package me.missionfamily.web.mission_family_be.business.account.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.QAccount;
import me.missionfamily.web.mission_family_be.domain.QUserInfo;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private QUserInfo userInfo = QUserInfo.userInfo;
    private QAccount account = QAccount.account;

    /**
     *
     * @param userInfo
     * @return 저장된 객체 키
     */
    public Long save(UserInfo userInfo){

        entityManager.persist(userInfo);
        return userInfo.getInfoId();
    }

    /**
     *
     * @param loginId
     * @return account
     */
    public Account findAccountById(String loginId, boolean isThrow) throws ServiceException {

        Account account = null;

        try {
            account = queryFactory
                    .selectFrom(this.account)
                    .where(this.account.userId.eq(loginId))
                    .fetchOne();
        } catch (Exception e) {

            if (Utils.isNull(account)) {
                log.info("There is no User that, which be registered With login identification. [ {} ]", loginId);
                if(isThrow) {
                    throw new ServiceException(MissionStatus.NOT_FOUND_USER);
                }
            }
        }
        return account;
    }

    /**
     *
     * @param loginId
     * @return userInfo
     */
    public UserInfo findUserInfoByUserId(String loginId) {
        UserInfo userInfo = queryFactory
                .selectFrom(this.userInfo)
                .where(this.userInfo.account.userId.eq(loginId))
                .fetchOne();

        if(Utils.isNull(userInfo)){
            log.info("There is no User that, which be registered With login identification. [ {} ]", loginId);
            throw new ServiceException(MissionStatus.NOT_FOUND_USER);
        }

        return userInfo;
    }




}
