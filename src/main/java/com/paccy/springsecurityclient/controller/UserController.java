package com.paccy.springsecurityclient.controller;

import com.paccy.springsecurityclient.entity.User;
import com.paccy.springsecurityclient.model.UserModel;
import com.paccy.springsecurityclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserModel userModel){
      return  userService.register(userModel);
    }
}
