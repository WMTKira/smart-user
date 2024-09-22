package com.wmt.smartuser.model;


import lombok.Data;
import java.time.LocalDate;



/**
 * @author wmtumanday
 */
@Data
public class User {

    private Long id;
    private String userName;
    private LocalDate lastLogin = LocalDate.now();
    private String email;
    private String password;
    private String token;

}
