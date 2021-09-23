package me.missionfamily.web.mission_family_be.common.transactional.listener;

import me.missionfamily.web.mission_family_be.common.transactional.MFTransactionalEventListener;
import me.missionfamily.web.mission_family_be.common.transactional.event.PushEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PushEventListener implements MFTransactionalEventListener<PushEvent> {

    @Override
    public void beforeCommit(PushEvent pushEvent) throws Throwable {

    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = PushEvent.class)
    public void afterCommit(PushEvent pushEvent) throws Throwable {

    }

    @Override
    public void afterRollback(PushEvent pushEvent) throws Throwable {

    }

    @Override
    public void afterCompletion(PushEvent pushEvent) throws Throwable {

    }

    @Override
    public void onApplicationEvent(PushEvent event) {

    }
}
