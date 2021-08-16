package me.missionfamily.web.mission_family_be.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.QUserInfo;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    QUserInfo userInfo = QUserInfo.userInfo;

    public UserInfo findOne(Long infoId){
        return em.find(UserInfo.class,infoId);
    }



    public List<UserInfo> findAll(){
        return queryFactory
                .selectFrom(userInfo)
                .fetch();
    }





}
