package com.zosh.service;

import com.zosh.modal.User;

public interface UserService {

    User findUserProfileByJwt(String jwt)throws Exception;

    User findUserByEmail(String email)throws Exception;

    User findUserById(Long userId)throws Exception;

    User updateUserProjectSize(User user, int number);
}
