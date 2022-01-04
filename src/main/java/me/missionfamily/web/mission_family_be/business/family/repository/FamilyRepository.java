package me.missionfamily.web.mission_family_be.business.family.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.QFamily;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.NoticeMessage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
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

    public void saveNotice(NoticeMessage message){

        entityManager.persist(message);

    }

    public void saveInvite(InviteMessage message){

        entityManager.persist(message);

    }

    public Family findFamilyGroupByKey(Long familyId){

        return queryFactory
                .selectFrom(family)
                .where(family.familyId.eq(familyId),
                        eqRole("GROUP"))
                .fetchOne();

    }

    public Family findFamilyMemberByKey(Long memberKey) throws ServiceException {
        Family member = queryFactory
                .selectFrom(this.family)
                .where(this.family.familyId.eq(memberKey),
                        eqRole("MEMBER"))
                .fetchOne();

        if(Utils.isNull(member)) {
            log.info("can't find family member by member key [ {} ]", memberKey);
            throw new ServiceException(MissionStatus.NOT_FOUND_FAMILIES);
        }

        return member;
    }


    private BooleanExpression eqRole(String role){
        if(Utils.isEmptyOrNull(role)){
            return null;
        }
        return family.role.eq(role);
    }


}
