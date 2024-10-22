package by.ustsinovich.fcadhack.controller.impl;

import by.ustsinovich.fcadhack.controller.AuthController;
import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.dto.request.LoginRequest;
import by.ustsinovich.fcadhack.dto.request.RefreshRequest;
import by.ustsinovich.fcadhack.dto.request.RegisterRequest;
import by.ustsinovich.fcadhack.dto.response.AuthResponse;
import by.ustsinovich.fcadhack.mapper.UserMapper;
import by.ustsinovich.fcadhack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    private final UserMapper userMapper;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        return userMapper.mapToDto(authService.register(registerRequest));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    public AuthResponse refresh(RefreshRequest refreshRequest) {
        return authService.refresh(refreshRequest);
    }

}
