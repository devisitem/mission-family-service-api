package me.missionfamily.web.mission_family_be.business.family.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.QFamily;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.NoticeMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.ServiceRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
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

        queryFactory
                .selectFrom(family)
                .where(family.familyKey.eq(foundAccount),
                        eqRole("MEMBER"))
                .fetch();

        return null;
    }

    public void saveNotice(NoticeMessage message){

        entityManager.persist(message);

    }

    public void saveInvite(InviteMessage message){

        entityManager.persist(message);

    }

    public Family findFamilyGroupByKey(Family group){

        queryFactory
                .selectFrom(family)
                .where(family.familyId.eq(group.getFamilyId()),
                        eqRole("GROUP"))
                .fetchOne();

    }


    private BooleanExpression eqRole(String role){
        if(MissionUtil.isEmptyOrNull(role)){
            return null;
        }
        return family.role.eq(role);
    }


}
