package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Family;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "mf_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ServiceRequest {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ServiceProperties typeClass;

    /**
     * 메세지 셋팅
     * @param title
     * @param content
     * @param typeClass
     */
    protected void setMessage(String title, String content, ServiceProperties typeClass ){
        this.title = title;
        this.content = content;
        this.typeClass = typeClass;
    }


    /**
     * 하위클래스 메세지 셋팅용
     * @param title
     * @param message
     * @param typeClass
     * @return
     */
    abstract ServiceRequest createRequest(Object messageSender, Object messageTarget, String title, String content, ServiceProperties typeClass);
}
