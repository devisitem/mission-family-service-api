package me.missionfamily.web.mission_family_be.common.transactional;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface MFTransactionalEventListener<T extends ApplicationEvent> extends ApplicationListener<T> {

    void beforeCommit (T t) throws Throwable;

    void afterCommit (T t) throws Throwable;

    void afterRollback (T t) throws Throwable;

    void afterCompletion (T t) throws Throwable;

}
