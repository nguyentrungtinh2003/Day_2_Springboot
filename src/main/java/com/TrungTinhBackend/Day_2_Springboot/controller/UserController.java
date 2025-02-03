package com.TrungTinhBackend.Day_2_Springboot.controller;

import com.TrungTinhBackend.Day_2_Springboot.dto.UserDTO;
import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;
import com.TrungTinhBackend.Day_2_Springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));
    }
}
