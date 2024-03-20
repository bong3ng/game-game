package com.gamevuifree.Service.AuthServiceImpl;

import com.gamevuifree.Entity.UserInfo;
import com.gamevuifree.JwtService;
import com.gamevuifree.Payload.Request.LoginRequest;
import com.gamevuifree.Payload.Request.RegisterRequest;
import com.gamevuifree.Payload.Response.LoginResponse;
import com.gamevuifree.Payload.ResponseData;
import com.gamevuifree.Repository.UserInfoRepository;
import com.gamevuifree.Service.AuthService;
import com.gamevuifree.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService  {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserInfoRepository repository;
    @Override
    public ResponseData login(LoginRequest request) {
        LoginResponse data = new LoginResponse();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            data.setAccessToken(jwtService.generateToken(request.getUsername()));
            return new ResponseData(true, "", data);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Override
    public ResponseData register(RegisterRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setRoles("ROLE_USER");
        userInfo.setName(request.getUsername());
        userInfo.setEmail(request.getEmail());

        //validate data
        //save db
        userInfo.setPassword(encoder.encode(request.getPassword()));
        repository.save(userInfo);
        return new ResponseData(true, "", userInfo);
    }

}
