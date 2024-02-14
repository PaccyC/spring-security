package com.paccy.springsecurityclient.service;

import com.paccy.springsecurityclient.entity.User;
import com.paccy.springsecurityclient.model.UserModel;
import com.paccy.springsecurityclient.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    public User register(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder().encode(userModel.getPassword()));

        userRepository.save(user);
        return user;

    }
}
