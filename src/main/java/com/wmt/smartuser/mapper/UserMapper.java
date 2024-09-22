package com.wmt.smartuser.mapper;

import com.wmt.smartuser.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wmtumanday
 */
@Mapper
public interface UserMapper {
    /**
     * @param userName
     * @return
     */
    User getUser(@Param("userName") String userName);

    int saveUser(User user);

    List<User> getUserList();
}
