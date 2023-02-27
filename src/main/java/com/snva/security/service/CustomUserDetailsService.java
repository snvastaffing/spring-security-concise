package com.snva.security.service;

import com.snva.security.modals.Role;
import com.snva.security.modals.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService iUserService;


    @Override
    @Transactional // ACID in database transactions
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserService.findUserByUserName(username);
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthority(user.getRoles());
        return buildUserForAuthentication(user, grantedAuthorities);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> grantedAuthorities) {
        return  new org.springframework.security.core.userdetails.User(
          user.getUserName(),
          user.getPassword(),
                user.getIsActive(),true,true,true,grantedAuthorities
        );
    }

    private List<GrantedAuthority> getGrantedAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }
}
