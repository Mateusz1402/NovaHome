package com.novahome.controllers;


import com.novahome.dto.LoginRequest;
import com.novahome.dto.RegisterRequest;
import com.novahome.functionalities.hub.CentralSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final CentralSystem centralSystem;

    public UserController(CentralSystem centralSystem){
        this.centralSystem = centralSystem;
    }

    @GetMapping("/is-logged")
    public ResponseEntity<Boolean> isLogged(){
        return ResponseEntity.ok(centralSystem.getLoginStatus());
    }

    @PatchMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest){
        centralSystem.login(loginRequest.name(), loginRequest.pass());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/logout")
    public ResponseEntity<Void> logout(){
        centralSystem.logout();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest){
        centralSystem.register(registerRequest.type(), registerRequest.name(), registerRequest.pass());
        return ResponseEntity.noContent().build();
    }
}
