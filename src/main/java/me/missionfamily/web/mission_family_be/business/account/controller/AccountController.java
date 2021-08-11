package me.missionfamily.web.mission_family_be.business.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.Request;
import me.missionfamily.web.mission_family_be.common.data_transfer.Response;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.business.account.dxo.UserDxo;
import me.missionfamily.web.mission_family_be.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@Slf4j
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/duplicateCheck")
    public ResponseEntity<Response> dupCheck (@RequestBody @Valid AccountDxo.Request request) throws Exception {
        String userId = request.getUserId();

        Response response = accountService.dupCheckById(userId);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity registerAccount (@RequestBody @Valid AccountDxo.Request request) throws Exception {

        AccountDxo.Response response = accountService.registerForAccount(request);

        return ResponseEntity.ok().body(response);
    }


}
