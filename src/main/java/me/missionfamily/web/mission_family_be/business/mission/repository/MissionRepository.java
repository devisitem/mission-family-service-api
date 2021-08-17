package me.missionfamily.web.mission_family_be.business.mission.repository;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.domain.Mission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class MissionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public void saveMission(Mission mission){

        entityManager.persist(mission);

    }
}
