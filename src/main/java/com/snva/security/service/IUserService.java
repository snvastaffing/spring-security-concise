package com.snva.security.service;

import com.snva.security.modals.User;

public interface IUserService {
    User findUserByEmail(String email);
    User findUserByUserName(String userName);
    User saveUser(User user);
}
