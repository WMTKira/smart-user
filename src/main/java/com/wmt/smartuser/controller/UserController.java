package com.wmt.smartuser.controller;

import com.wmt.smartuser.dto.UserDto;
import com.wmt.smartuser.model.User;
import com.wmt.smartuser.service.UserService;
import com.wmt.smartuser.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wmtumanday
 */
@Slf4j
@RestController
@RequestMapping(value = "/smart/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/register")
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        int succeed = userService.register(userDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Object> login(@RequestBody UserDto userDto) {
        User user1 = userService.login(userDto);
        return ResponseVo.success(user1);
    }

    @GetMapping("/v1/getUserList")
    public List<User> test() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1025115877752L);
        user1.setUserName("admin");
        user1.setEmail("admin@wwcloud.com");
        users.add(user1);
        return users;
    }

}
