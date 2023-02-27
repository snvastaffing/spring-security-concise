package com.snva.security.config;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


// spring boot security provides adaptors to override the default configuration and lets you use security in
// your own way
//@Configuration
public class MyAppSecurityConfig  {

    // this is for authorization
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/student").hasRole("STUDENT")
                .and()
                .csrf().disable();
    }


    // this is for authentication

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("student").password("{noop}studentpassword").roles("STUDENT")
                .and()
                .withUser("admin").password("{noop}adminpassword").roles("ADMIN","STUDENT");
    }
}
//
//@Configuration
//@EnableWebSecurity
//public class MyAppSecurityConfig {
//
//    @org.springframework.beans.factory.annotation.Autowired
//    private UserDetailsService userDetailsService;
//
////    @org.springframework.beans.factory.annotation.Autowired
////    private  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//        RequestMatcher matcher= new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return false;
//            }
//        };
//
//        RequestMatcher[] adminRequestMatchers= {
//        new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return request.getRequestURI().matches("/admin");
//            }
//        }
//        ,new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return request.getRequestURI().matches("/student");
//            }
//        }
//        ,new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return request.getRequestURI().matches("/");
//            }
//        }};
//
//        RequestMatcher[] studentRequestMatchers= {
//
//                new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return request.getRequestURI().matches("/student");
//            }
//        }
//                ,new RequestMatcher() {
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                return request.getRequestURI().matches("/");
//            }
//        }};
//
//
//        httpSecurity
//                .authorizeHttpRequests(authorise ->authorise
//
//                        .requestMatchers(studentRequestMatchers).hasRole("STUDENT")
//                        .requestMatchers(adminRequestMatchers).hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(Customizer.withDefaults());
//
////                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers( {"/","/home"}).permitAll()
////                        .anyRequest().authenticated()
////                )
////                .formLogin((form) -> form
////                        .loginPage("/login")
////                        .permitAll())
////                .logout((logout) -> logout.permitAll());
//
//        return  httpSecurity.build();
//    }
//    @Bean
//    org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder() {
//        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService(){
////        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
////        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("admin").password("adminpassword").roles("ADMIN","STUDENT","USER").build());
////        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("student").password("studentpassword").roles("STUDENT").build());
////        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("user").password("userpassword").roles("USER").build());
////        return  inMemoryUserDetailsManager;
////    }
//
//    // implementation for security here
//
//}
//
////@EnableWebSecurity
////public class MyAppSecurityConfig {
////
////}
