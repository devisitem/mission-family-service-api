package me.missionfamily.web.mission_family_be.repository;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository{


    private final EntityManager em;

    public void save(UserInfo userinfo){
        em.persist(userinfo);
    }

    public UserInfo findOne(Long infoId){
        return em.find(UserInfo.class,infoId);
    }


    public void accountLogIn(Account account){

    }
}
