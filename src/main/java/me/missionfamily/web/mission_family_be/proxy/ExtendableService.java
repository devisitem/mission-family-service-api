package me.missionfamily.web.mission_family_be.proxy;

import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface ExtendableService<T> {

    void saveOne(T t);

    void saveAll(List<T> list);

    T findOne(Long key);

    List<T> findAll(Object key);

    Long updateOne(Object target);

}
