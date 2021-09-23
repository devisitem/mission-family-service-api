package me.missionfamily.web.mission_family_be.common.transactional.event;

import me.missionfamily.web.mission_family_be.common.transactional.model.MessageModel;
import org.springframework.context.ApplicationEvent;


public class PushEvent extends ApplicationEvent {

    private MessageModel message;

    public PushEvent(Object source, MessageModel message) {
        super(source);
        this.message = message;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

}
