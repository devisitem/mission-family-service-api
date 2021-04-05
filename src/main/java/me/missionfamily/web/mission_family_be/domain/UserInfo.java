package me.missionfamily.web.mission_family_be.domain;

import lombok.*;
import org.springframework.util.Assert;

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
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Id
    @Column(name = "mf_info_key")
    private Long infoId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    @Column(name = "mf_user_name",length = 30)
    private String userName;

    @Column(name = "mf_user_phone",length = 30)
    private String userPhone;

    @Column(name = "mf_user_birth",length = 12)
    private String userBirth;

    @Column(name = "mf_auth_key",length = 500)
    private String authKey;

    @Builder
    public UserInfo(String userName,String userPhone,String userBirth,Account account){
        Assert.hasText(userName,"사용자 이름은 빈값일 수 없습니다.");
        Assert.hasText(userPhone,"사용자 연락처는 빈값일 수 없습니다.");
        Assert.hasText(userBirth,"사용자 생년월일은 빈값일 수 없습니다.");

        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.account = account;
    }
    public void setAccount(Account account){
        this.account = account;
        account.setUserInfo(this);
    }


}
