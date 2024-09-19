package com.wmt.smartuser.model;


import lombok.Data;


/**
 * @author wmtumanday
 */
@Data
public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String token;

}
