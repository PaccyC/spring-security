package com.paccy.springsecurityclient.controller;

import com.paccy.springsecurityclient.config.JwtUtils;
import com.paccy.springsecurityclient.dto.AuthenticationRequest;
import com.paccy.springsecurityclient.service.UserDetailsS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailsS userDetailsS;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            final UserDetails user = userDetailsS.userDetailsService().loadUserByUsername(request.getEmail());
            log.info(user.toString());
            return ResponseEntity.ok().body("successfully login ");

        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return ResponseEntity.status(400).body("Some error occured");

    }
}
