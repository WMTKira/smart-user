package com.wmt.smartuser.service;

import com.wmt.smartuser.dto.UserDto;
import com.wmt.smartuser.model.User;

import java.util.List;

/**
 * @author wmtumanday
 */
public interface UserService {


    User login(UserDto userDto);

    int register(UserDto userDto);

    User getInfo();

    List<User> getUserList();
}
