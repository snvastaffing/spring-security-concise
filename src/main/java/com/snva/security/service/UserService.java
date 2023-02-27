package com.snva.security.service;

import com.snva.security.modals.Role;
import com.snva.security.modals.User;
import com.snva.security.repository.RoleRepository;
import com.snva.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements  IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return  userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {
        return  userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        Role role = roleRepository.findByRole("ADMIN");
        return userRepository.save(user);
    }
}
