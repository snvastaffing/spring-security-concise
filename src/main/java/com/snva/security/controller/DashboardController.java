package com.snva.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {


    @GetMapping("/dashboard")
    public ModelAndView dashBoard(@AuthenticationPrincipal OAuth2User principal){
        //  Collections.singletonMap("name", principal.getAttribute("name"));
        // use social media
        principal.getAttributes().forEach((x,y)->
            System.out.println(x+"--"+y)
        );
        ModelAndView modelAndView = new ModelAndView("index",principal.getAttributes());
        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView dashBoard(){
        //  Collections.singletonMap("name", principal.getAttribute("name"));
        // use social media

        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


}
