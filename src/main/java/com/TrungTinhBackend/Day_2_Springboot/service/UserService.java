package com.TrungTinhBackend.Day_2_Springboot.service;

import com.TrungTinhBackend.Day_2_Springboot.dto.UserDTO;
import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;

public interface UserService {
    public APIResponse register(UserDTO userDTO);
    public APIResponse login(UserDTO userDTO);
    public APIResponse getAllUser();
    public APIResponse getUserById(Long id);
    public APIResponse updateUser(Long id, UserDTO userDTO);
    public APIResponse deleteUser(Long id);
}
