package com.snva.security.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController {

    @GetMapping("/")
    public  String publicResource(){
        return  "this is a public resource and accessible to everyone";
    }

    @GetMapping("/admin")
    public  String adminResource(){
        return  "this is an admin resource and accessible to admin only ";
    }

    @GetMapping("/student")
    public  String studentResource(){
        return  "this is an student resource and accessible to students only ";
    }


//    BasicAuthenticationFilter
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal){

        return principal.getAttributes();//  Collections.singletonMap("name", principal.getAttribute("name"));
        // use social media
    }
}
