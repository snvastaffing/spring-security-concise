package com.snva.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class OpenIdConnectSecurityConfig extends WebSecurityConfigurerAdapter {




//    org.springframework.security.web.session.DisableEncodeUrlFilter
    @Override
//    OAuth2LoginAuthenticationFilter
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> request
                        .antMatchers("/", "index.html", "/user").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .oauth2Login(oauth -> oauth.successHandler((request, response, auth) -> {
                    response.sendRedirect("/dashboard");
                }));
    }
}
