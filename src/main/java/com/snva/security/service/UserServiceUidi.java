package com.snva.security.service;

import com.snva.security.modals.SocialMediaAccount;
import com.snva.security.modals.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceUidi extends  UserService{

    List<String> socialMediaAccounts = new ArrayList<>();
     UserServiceUidi(){
        socialMediaAccounts.addAll(Arrays.asList("facebook","google","tumbler","linkedin","instagram","github"));
     }

    @Override
    public User saveUser(User user) {
        List<SocialMediaAccount> socialMediaAccountList = fetchSocialMediaAccountsByEmail(user.getEmail());
        return super.saveUser(user);
    }

    private List<SocialMediaAccount> fetchSocialMediaAccountsByEmail(String email) {
            return  null;
            // all the api integrations for the socialMediaAccounts
    }
}
