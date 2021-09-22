package me.missionfamily.web.mission_family_be.common.transactional.event;

import org.springframework.context.ApplicationEvent;

public class PushEvent extends ApplicationEvent {

    private String title;

    private String content;

    private String sender;

    public PushEvent(Object source) {


    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

}
