package me.missionfamily.web.mission_family_be.business.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseData;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements ResponseData {


    @JsonProperty("checked_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String checkedId;
}
