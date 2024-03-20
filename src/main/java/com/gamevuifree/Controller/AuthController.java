package com.gamevuifree.Controller;

import com.gamevuifree.Payload.Request.LoginRequest;
import com.gamevuifree.Payload.Request.RegisterRequest;
import com.gamevuifree.Payload.ResponseData;
import com.gamevuifree.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseData register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }


}
