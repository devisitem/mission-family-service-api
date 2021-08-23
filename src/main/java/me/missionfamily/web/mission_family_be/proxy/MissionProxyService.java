package me.missionfamily.web.mission_family_be.proxy;

import lombok.RequiredArgsConstructor;
import me.missionfamily.web.mission_family_be.business.mission.dxo.MissionDxo;
import me.missionfamily.web.mission_family_be.business.mission.model.MissionModel;
import me.missionfamily.web.mission_family_be.business.mission.service.MissionService;
import me.missionfamily.web.mission_family_be.domain.Mission;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionProxyService implements ExtendableService<Mission>{

    private final MissionService missionService;

    @Override
    public void saveOne(Mission mission) {
        System.out.println("---------- MissionProxyService.saveOne  start ----------");
        System.out.println("save this mission = " +mission);

        MissionDxo.Request request = new MissionDxo.Request();
        request.setMission(MissionModel.builder()
                .missionType("GROUP")
                .missionContent("젠킨스 공유방")
                .missionContent("모두가 함께하는 젠킨스 공유 오픈채팅방 입니다.")
                .build());
        missionService.createMission(request);

        System.out.println("---------- MissionProxyService.saveOne  end ----------");
    }

    @Override
    public void saveAll(List<Mission> list) {
        System.out.println("save all mission = " + list);
    }

    @Override
    public Mission findOne(Long key) {
        System.out.println("find mission by key = " + key);
        return null;
    }

    @Override
    public List<Mission> findAll(Object key) {
        System.out.println("find mission list by common key = " + key);
        return null;
    }

    @Override
    public Long updateOne(Object target) {
        System.out.println("update mission with = " + target);
        return null;
    }
}
