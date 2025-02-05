package com.TrungTinhBackend.Day_2_Springboot.controller;

import com.TrungTinhBackend.Day_2_Springboot.dto.UserDTO;
import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;
import com.TrungTinhBackend.Day_2_Springboot.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
