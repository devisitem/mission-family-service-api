package me.missionfamily.web.mission_family_be.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.domain.ServerResponse;
import me.missionfamily.web.mission_family_be.dto.UserDto;
import me.missionfamily.web.mission_family_be.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;
    private ServerResponse serverResponse;

    @PostMapping("/dupCheck")
    public ResponseEntity dupCheck (@RequestBody String checkId) throws Exception {
        System.out.println("checkId = " + checkId);
        if(accountService.dupCheckById(checkId)) {
            serverResponse = new ServerResponse("code_no_user","not found of user");
        } else {
            serverResponse = new ServerResponse("already_exist_user","not found of user");
        }


        return new ResponseEntity<ServerResponse>(serverResponse,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerAccount (HttpServletResponse response, @RequestBody @Valid UserDto dto) throws Exception {

        Account account = accountService.accountRegister(dto);
        serverResponse = new ServerResponse("create_success","유저 생성에 성공하였습니다.");


        return new ResponseEntity<>(serverResponse,HttpStatus.OK);
    }


}
