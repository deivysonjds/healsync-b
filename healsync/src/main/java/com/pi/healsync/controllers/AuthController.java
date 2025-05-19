package com.pi.healsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.Auth;
import com.pi.healsync.exceptions.NoSuchException;
import com.pi.healsync.exceptions.PasswordInvalid;
import com.pi.healsync.services.AuthService;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> authProfile(@RequestBody Auth auth){
        String token;
        try {
            token = service.authenticateUser(auth.getEmail(), auth.getPassword());
        } catch (NoSuchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado");
        } catch (PasswordInvalid e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciais inválidas");
        }
        
        return ResponseEntity.ok().body(token);
    }
}
