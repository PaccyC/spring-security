package com.paccy.springsecurityclient.service;

import com.paccy.springsecurityclient.entity.User;
import com.paccy.springsecurityclient.model.UserModel;


public interface UserService {



    User register(UserModel userModel);
}
