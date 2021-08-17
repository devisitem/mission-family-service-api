package me.missionfamily.web.mission_family_be.business.account.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.QAccount;
import me.missionfamily.web.mission_family_be.domain.QUserInfo;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    QUserInfo userInfo = QUserInfo.userInfo;
    QAccount account = QAccount.account;

    /**
     *
     * @param userInfo
     * @return 저장된 객체 키
     */
    public Long save(UserInfo userInfo){

        entityManager.persist(userInfo);
        return userInfo.getInfoId();
    }

    @EntityGraph(attributePaths = "roles")
    public Optional<Account> findOneByUserId(String userId) {
        return Optional.ofNullable(queryFactory.selectFrom(account)
                .where(account.userId.eq(userId))
                .fetchOne());
    }

    public Account findAccountById(String id) {
        Account account = queryFactory
                .selectFrom(this.account)
                .where(this.account.userId.eq(id))
                .fetchOne();

        return account;
    }

    public UserInfo findUserInfoByUserId(String userId) {
        UserInfo userInfo = queryFactory
                .selectFrom(this.userInfo)
                .where(this.userInfo.account.userId.eq(userId))
                .fetchOne();

        return userInfo;
    }




}
