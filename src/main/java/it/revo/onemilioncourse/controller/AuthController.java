package it.revo.onemilioncourse.controller;

import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.repository.UserRepository;
import it.revo.onemilioncourse.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final
    AuthService authService;

    private final
    UserRepository authRepository;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(name = "password", required = false) String password) {
        ApiResponse<?> apiResponse = authService.login(phoneNumber, password);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
