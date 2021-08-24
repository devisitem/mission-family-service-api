package me.missionfamily.web.mission_family_be.domain;

import com.sun.javafx.geom.transform.Identity;
import javassist.bytecode.ByteArray;
import lombok.*;
import me.missionfamily.web.mission_family_be.common.util.MissionUtil;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Base64;
import java.util.UUID;

@Entity
@Getter
@DynamicUpdate
@Table(name = "mf_user_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_key")
    private Long infoId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_Key")
    private Account account;

    @Column(name = "mf_user_name",length = 30)
    private String userName;

    @Column(name = "mf_user_phone",length = 30, unique = true)
    private String userPhone;

    @Column(name = "mf_user_birth",length = 12)
    private String userBirth;

    @Column(name = "mf_auth_key",length = 500)
    private String authKey;

    @Builder
    public UserInfo(String userName,String userPhone,String userBirth, Account account){
        Assert.hasText(userName,"사용자 이름은 빈값일 수 없습니다.");
        /* Assert.hasText(userPhone,"사용자 연락처는 빈값일 수 없습니다.");
        Assert.hasText(userBirth,"사용자 생년월일은 빈값일 수 없습니다."); */

        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.account = account;
    }

    public void setAccount(Account account){
        this.account = account;
        account.addUserInfo(this);
    }

    /**
     * 인증키 생성 및 변경
     * @param
     * @return
     */
    public String generateAndRefreshAuthKey() {

        this.authKey = UUID.randomUUID().toString().replaceAll("-", "");
        return this.authKey;
    }


}
