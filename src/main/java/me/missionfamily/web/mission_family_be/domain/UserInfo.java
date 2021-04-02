package me.missionfamily.web.mission_family_be.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(
        name = "mf_user_info",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "mf_user_phone"
                )
        }
)
public class UserInfo {

    @Id @GeneratedValue
    @Column(name = "mf_info_key")
    private Long infoId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_info_user")
    private Account account;

    @Column(name = "mf_user_name",length = 30)
    private String userName;

    @Column(name = "mf_user_phone",length = 30)
    private String userPhone;

    @Column(name = "mf_user_birth",length = 12)
    private String userBirth;

    @Column(name = "mf_auth_key",length = 500)
    private String authKey;


}
