package me.missionfamily.web.mission_family_be.business.account.controller;

import me.missionfamily.web.mission_family_be.common.AbstractControllerTest;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractControllerTest {

    @Test
    @DisplayName("[아이디 중복확인] 정상")
    public void dupCheck() throws Throwable {
        /* Given */
        String userId = "Kimchi-dev";

        /* When */
        ResultActions result = perform(HttpMethod.GET, "/api/users/check/duplicate", userId);

        /* Then */
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("result.code").value(MissionStatus.SUCCESS.getCode()))
                .andExpect(jsonPath("res_data.checked_id").value(userId))
                .andDo(documentation(
                        requestFields(
                                field("user_id", "중복 확인할 아이디", String.class)
                        ),
                        responseFields(
                                field("result.code", "결과 코드", Integer.class),
                                field("res_data.checked_id", "사용가능 아이디", String.class)
                        )
                ));

    }

}