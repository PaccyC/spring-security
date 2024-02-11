package com.paccy.springsecurityclient.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsS {
    @Override
    public UserDetailsService userDetailsService() {
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return APPLICATION_USERS
                        .stream()
                        .filter(u-> u.getUsername().equals(email))
                        .findFirst()
                        .orElseThrow(()->new UsernameNotFoundException("No user was found"));
            }
        };
    }


    private final static List<UserDetails> APPLICATION_USERS= Arrays.asList(
            new org.springframework.security.core.userdetails.User("paccy@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE ADMIN"))),
            new org.springframework.security.core.userdetails.User("user@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE USER")))
    );
}
