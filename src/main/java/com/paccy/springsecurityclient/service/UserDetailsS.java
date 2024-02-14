package com.paccy.springsecurityclient.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface UserDetailsS {
//    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    public UserDetailsService userDetailsService();

}
