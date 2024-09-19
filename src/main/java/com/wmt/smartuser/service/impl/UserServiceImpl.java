package com.wmt.smartuser.service.impl;

import com.wmt.smartuser.dto.UserDto;
import com.wmt.smartuser.mapper.UserMapper;
import com.wmt.smartuser.model.User;
import com.wmt.smartuser.model.UserContext;
import com.wmt.smartuser.service.UserService;
import com.wmt.smartuser.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;


/**
 * @author wmtumanday
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper usermapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User credential = usermapper.getUser(username);
        if (ObjectUtils.isEmpty(credential)) {
            log.info("User empty ");
            throw new UsernameNotFoundException("User not exist :" + username);
        }
        return new UserContext(credential);
    }

    @Override
    public User login(UserDto userDto) {
        User retUser = new User();
        retUser.setId(1025115877752L);
        retUser.setUserName(userDto.getUserName());
        retUser.setEmail(userDto.getUserName() + "@wwcloud.com");

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        if (authenticate.isAuthenticated()) {
            retUser.setToken(jwtUtil.generateToken(userDto.getUserName()));
        } else {
            throw new BadCredentialsException("invalid access");
        }
        return retUser;
    }

    @Override
    public int register(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getUserName() + "@wwcloud.com");
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return usermapper.saveUser(user);
    }
}
