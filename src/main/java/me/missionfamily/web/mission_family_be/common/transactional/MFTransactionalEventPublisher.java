package me.missionfamily.web.mission_family_be.common.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MFTransactionalEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(ApplicationEvent event) {

        eventPublisher.publishEvent(event);
    }

}
