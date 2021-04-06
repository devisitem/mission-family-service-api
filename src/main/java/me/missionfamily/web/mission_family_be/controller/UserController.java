package me.missionfamily.web.mission_family_be.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;


    @PostMapping("/dupCheck")
    public Map<String,Object> dupCheck (@RequestBody Map<String,Object> clientMap) throws Exception {
        String checkId = (String) clientMap.get("checkId");

        if(accountService.dupCheckById(checkId)) {
            clientMap.put("serviceCode", "code_no_user");
        } else {
            clientMap.put("serviceCode", "already_exist_user");
        }


        return clientMap;
    }

    @PostMapping("/regist")
    public Map<String,Object> registerAccount (HttpServletResponse response,@RequestBody Map<String,Object> clientMap) throws Exception {
        log.info("clientMap = {} ",clientMap);
        String serviceCode = accountService.accountRegister(clientMap);

        clientMap.put("serviceCode",serviceCode);


        return clientMap;
    }

    @PostMapping("/login")
    public String loginAccount(@RequestBody Map<String,Object> clientMap ) throws Exception {

        String result = "";
        if(accountService.loginProcess(clientMap)){
            result = "ok";
        } else {
            result = "Not_ok";
        }

        return result;
    }
}
