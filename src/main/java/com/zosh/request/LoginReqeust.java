package com.zosh.request;

import lombok.Data;

@Data
public class LoginReqeust {
    private String email;
    private String password;
}
