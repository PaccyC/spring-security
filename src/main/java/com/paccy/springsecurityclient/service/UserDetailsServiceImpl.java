package com.paccy.springsecurityclient.service;

import com.paccy.springsecurityclient.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsS {
//    @Override
//    public UserDetailsService userDetailsService() {
//        return  new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                return APPLICATION_USERS
//                        .stream()
//                        .filter(u-> u.getUsername().equals(email) )
//                        .findFirst()
//                        .orElseThrow(()->new UsernameNotFoundException("No user was found"));
//            }
//        };
//    }
//
//
//
//}


import com.paccy.springsecurityclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsS {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByEmail(email);
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//
//                .build();
//    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }
}

