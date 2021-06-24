package me.missionfamily.web.mission_family_be.repository;

import com.querydsl.core.QueryFactory;
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
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    QUserInfo userInfo = QUserInfo.userInfo;
    QAccount account = QAccount.account;


    public Account save(UserInfo userInfo){
        em.persist(userInfo);
        return userInfo.getAccount();
    }

    @EntityGraph(attributePaths = "roles")
    public Optional<Account> findOneByUserId(String userId){
        return Optional.ofNullable(queryFactory.selectFrom(account)
                .where(account.userId.eq(userId))
                .fetchOne());
    }

    public Account findById(String id){
        return queryFactory.selectFrom(account)
                .where(account.userId.eq(id))
                .fetchOne();
    }






}
