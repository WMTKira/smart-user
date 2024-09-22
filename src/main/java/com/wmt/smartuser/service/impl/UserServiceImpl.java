package com.wmt.smartuser.service.impl;

import com.wmt.smartuser.dto.UserDto;
import com.wmt.smartuser.mapper.UserMapper;
import com.wmt.smartuser.model.User;
import com.wmt.smartuser.model.UserContext;
import com.wmt.smartuser.service.UserService;
import com.wmt.smartuser.util.AssertUtil;
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
import org.springframework.util.StringUtils;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


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

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User credential = usermapper.getUser(username);
        if (ObjectUtils.isEmpty(credential)) {
            log.info("User empty ");
            throw new IllegalArgumentException("Username does not exist!");
        }
        return new UserContext(credential);
    }

    @Override
    public User login(UserDto userDto) {
        User retUser = new User();
        retUser.setId(1025115877752L);
        retUser.setUserName(userDto.getUserName());
        retUser.setEmail(userDto.getUserName() + "@wwcloud.com");
        log.info("authenticate");
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
            if (authenticate.isAuthenticated()) {
                retUser.setToken(jwtUtil.generateToken(userDto.getUserName()));
            }
            log.info("authenticate");
        }catch (Exception e) {
            log.error(String.valueOf(e));
            throw new IllegalArgumentException(e instanceof BadCredentialsException ? "Password is incorrect!" : e.getMessage());
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

    @Override
    public User getInfo() {
        String name = httpServletRequest.getHeader("NAME");
        AssertUtil.isFalse("".equals(name), "User not found, please login again!");
        return usermapper.getUser(name);
    }

    @Override
    public List<User> getUserList() {
        return usermapper.getUserList();
    }
}
