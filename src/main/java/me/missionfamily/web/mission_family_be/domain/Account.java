package me.missionfamily.web.mission_family_be.domain;

import lombok.*;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.dto.UserRole;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    @Column(name = "mf_role")
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "mf_user_id", referencedColumnName = "mf_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "mf_role_name",referencedColumnName = "role_name")})
    private Set<UserRole> roles;

    @Column(name = "mf_signup_date")
    private LocalDateTime signUpDate;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account",fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Family> myFamilies = new ArrayList<>();

    @OneToMany(mappedBy = "familyKey",cascade = CascadeType.ALL)
    private List<Family> belongFamily = new ArrayList<>();

    private boolean activated;

    public void setPassword(String password){
        this.userPassword = password;
    }

    public void signUpAccount(AccountDxo.Request dxo){
        this.userId = dxo.getUserId();
        this.userPassword = dxo.getPassword();
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
        userInfo.setAccount(this);
    }





}
