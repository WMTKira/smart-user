package com.wmt.smartuser.controller;

import com.wmt.smartuser.dto.UserDto;
import com.wmt.smartuser.model.User;
import com.wmt.smartuser.service.UserService;
import com.wmt.smartuser.util.AssertUtil;
import com.wmt.smartuser.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/register")
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        AssertUtil.isTrue(false,"dwdwdwdw");
        int succeed = userService.register(userDto);
        return succeed > 0 ? ResponseVo.success() : ResponseVo.fail();
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Object> login(@RequestBody UserDto userDto) {
        User user1 = userService.login(userDto);
        return ResponseVo.success(user1);
    }

    @GetMapping("/v1/getInfo")
    public ResponseEntity<Object> getInfo() {
        return ResponseVo.success(userService.getInfo());
    }

    @GetMapping("/v1/getUserList")
    public ResponseEntity<Object> test() {
        return ResponseVo.success(userService.getUserList());
    }

}
