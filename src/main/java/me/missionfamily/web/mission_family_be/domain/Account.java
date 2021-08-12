package me.missionfamily.web.mission_family_be.domain;

import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mf_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_key")
    private Long id;

    @Column(name = "mf_user_id",length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "mf_user_password",length = 800)
    private String userPassword;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_signup_date")
    private LocalDateTime signUpDate;

    @OneToOne(mappedBy = "account",fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Family> myFamilies = new ArrayList<>();

    @OneToMany(mappedBy = "familyKey",cascade = CascadeType.ALL)
    private List<Family> belongFamily = new ArrayList<>();

    private boolean activated;

    public void setPassword(String password){
        this.userPassword = password;
    }

    @Builder
    public Account(AccountDxo.Request dxo, UserInfo userInfo){
        this.userId = dxo.getUserId();
        this.userPassword = dxo.getPassword();
        this.userInfo = userInfo;
        this.deleteYn = "N";
        this.activated = true;
        this.signUpDate = LocalDateTime.now();
    }

    public void addUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
        userInfo.setAccount(this);
    }





}
