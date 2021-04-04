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

    @GetMapping("/hello")
    public String testHello (HttpServletRequest request){
        return "hello";
    }

    @PostMapping("/dupCheck")
    public Map<String,Object> dupCheck (HttpServletResponse response, HttpServletRequest request,@RequestBody Map<String,Object> clientMap){
        String checkId = (String) clientMap.get("checkId");

        System.out.println("checkId = " + checkId);

        if(accountService.dupCheckById(checkId)) {
            clientMap.put("serviceCode", "code_no_user");
        } else {
            clientMap.put("serviceCode", "already_exist_user");
        }


        return clientMap;
    }
}
