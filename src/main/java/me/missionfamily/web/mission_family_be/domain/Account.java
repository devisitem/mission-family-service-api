package me.missionfamily.web.mission_family_be.domain;

import com.mysema.commons.lang.Assert;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(
        name = "mf_account",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"mf_user_id"}
            )
        }
    )
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {


    @Id @GeneratedValue
    @Column(name = "mf_user_key")
    private Long userKey;

    @Column(name = "mf_user_id",length = 50, nullable = false)
    private String userId;

    @Column(name = "mf_user_password",length = 800)
    private String userPassword;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_signup_date")
    private LocalDateTime signUpDate;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account",fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Family> myFamilies = new ArrayList<>();

    @OneToMany(mappedBy = "familyKey",cascade = CascadeType.ALL)
    private List<Family> belongFamily = new ArrayList<>();

    @Builder
    public Account(String userId,UserInfo userInfo,String deleteYn){
        Assert.hasText(userId,"아이디는 빈 값일 수 없습니다.");

        this.userId = userId;
        this.userInfo = userInfo;
        this.deleteYn = "N";
        this.signUpDate = LocalDateTime.now();
    }

    public void setPassword(String password){
        this.userPassword = password;
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
        userInfo.setAccount(this);
    }





}
