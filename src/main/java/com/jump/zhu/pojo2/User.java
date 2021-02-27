package com.jump.zhu.pojo2;


import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Date create_time;
    private String state;
    private Date last_login_time;
    private String nickname;
    private String realname;

}
