package Mamadou.com.SecureVault.controller;


import Mamadou.com.SecureVault.dto.LoginRequest;
import Mamadou.com.SecureVault.dto.RegisterRequest;
import Mamadou.com.SecureVault.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
      return service.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@Valid @RequestBody LoginRequest request){
        return service.login(request);
    }

    @GetMapping("/me")
    public ResponseEntity<?> userProfile(){
      return service.userProfile();
    }
}
