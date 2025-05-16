package com.pi.healsync.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.healsync.DTO.Authentication;

@RestController
@RequestMapping
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> authProfile(@RequestBody Authentication auth){
        String user = auth.getEmail();
        System.out.println("teste");
        return ResponseEntity.ok().body(user);
    }
}
