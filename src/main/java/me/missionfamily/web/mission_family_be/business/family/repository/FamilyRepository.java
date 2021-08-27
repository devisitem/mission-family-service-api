package me.missionfamily.web.mission_family_be.business.family.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.QFamily;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FamilyRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    QFamily family = QFamily.family;


    public Long save(Family newerFamily) {

        entityManager.persist(newerFamily);

        return newerFamily.getFamilyId();
    }

    /**
     *
     * @param foundAccount
     * @return
     */
    public List<Family> findFamiliesByAccount(Account foundAccount) {



        return null;
    }
}
