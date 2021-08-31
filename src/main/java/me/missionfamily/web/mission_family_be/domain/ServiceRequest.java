package me.missionfamily.web.mission_family_be.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "mf_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceRequest {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String message;

    @Enumerated(EnumType.STRING)
    private ServiceProperties typeClass;

    public static ServiceRequest createRequest(String title, String message, ServiceProperties typeClass){
        ServiceRequest req = new ServiceRequest();
        req.title = title;
        req.message = message;
        req.typeClass = typeClass;

        return req;
    }
}
