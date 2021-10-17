package me.missionfamily.web.mission_family_be.business.family.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.common.exception.HttpResponseStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.QFamily;
import me.missionfamily.web.mission_family_be.domain.service_request.InviteMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.NoticeMessage;
import me.missionfamily.web.mission_family_be.domain.service_request.ServiceRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

        if(MissionUtil.isNull(member)) {
            log.info("can't find family member by member key [ {} ]", memberKey);
            throw new ServiceException(HttpResponseStatus.NOT_FOUND_FAMILIES);
        }

        return member;
    }


    private BooleanExpression eqRole(String role){
        if(MissionUtil.isEmptyOrNull(role)){
            return null;
        }
        return family.role.eq(role);
    }


}
