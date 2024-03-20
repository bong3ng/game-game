package com.gamevuifree.Service;

import com.gamevuifree.Payload.Request.LoginRequest;
import com.gamevuifree.Payload.Request.RegisterRequest;
import com.gamevuifree.Payload.ResponseData;

public interface AuthService {
    ResponseData login (LoginRequest request);
    ResponseData register (RegisterRequest request);
}
