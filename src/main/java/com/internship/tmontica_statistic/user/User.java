package com.internship.tmontica_statistic.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private Date birthDate;
    private String password;
    private String passwordCheck;
    private String role;
    private Date createdDate;
    private int point;
    private boolean isActive;
    private String activateCode;
}
