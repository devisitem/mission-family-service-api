package me.missionfamily.web.mission_family_be.business.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    @NotEmpty
    @JsonProperty("mission_signature")
    private String missionSignature;

    @NotEmpty
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("login_id")
    private String loginId;

}
