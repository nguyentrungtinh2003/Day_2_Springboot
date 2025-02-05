package com.TrungTinhBackend.Day_2_Springboot.service;

import com.TrungTinhBackend.Day_2_Springboot.dto.UserDTO;
import com.TrungTinhBackend.Day_2_Springboot.entity.User;
import com.TrungTinhBackend.Day_2_Springboot.enums.UserProvider;
import com.TrungTinhBackend.Day_2_Springboot.enums.UserRole;
import com.TrungTinhBackend.Day_2_Springboot.repository.UserRepository;
import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;
import com.TrungTinhBackend.Day_2_Springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public APIResponse register(UserDTO userDTO) {
        APIResponse apiResponse = new APIResponse();
        User user = userRepository.findByEmail(userDTO.getEmail());

        if(user != null) {
            apiResponse.setStatusCode(500L);
            apiResponse.setMessage("Email already exists");
            apiResponse.setTimestamp(LocalDateTime.now());

            return apiResponse;
        }

        User user1 = new User();
        user1.setUsername(userDTO.getUsername());
        user1.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user1.setEmail(userDTO.getEmail());
        user1.setDate(LocalDateTime.now());
        user1.setRole(UserRole.USER);
        user1.setProvider(UserProvider.LOCAL);

        userRepository.save(user1);

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("User register success !");
        apiResponse.setData(user1);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }

    public APIResponse login(UserDTO userDTO) {
        APIResponse apiResponse = new APIResponse();

        User user = userRepository.findByUsername(userDTO.getUsername());

        if(!passwordEncoder.matches(userDTO.getPassword(),user.getPassword())) {
            apiResponse.setStatusCode(500L);
            apiResponse.setMessage("Login fail !");
            apiResponse.setTimestamp(LocalDateTime.now());
        }

        String token = jwtUtil.generateToken(userDTO.getUsername());

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("Login success !");
        apiResponse.setData(token);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }

    @Override
    public APIResponse getAllUser() {
        APIResponse apiResponse = new APIResponse();
        List<User> users = userRepository.findAll();

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("Get all user success !");
        apiResponse.setData(users);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }

    @Override
    public APIResponse getUserById(Long id) {
        APIResponse apiResponse = new APIResponse();
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found !")
        );

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("Get user by id success !");
        apiResponse.setData(user);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }

    @Override
    public APIResponse updateUser(Long id, UserDTO userDTO) {
        APIResponse apiResponse = new APIResponse();
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found !")
        );

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("Update user success !");
        apiResponse.setData(user);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }

    @Override
    public APIResponse deleteUser(Long id) {
        APIResponse apiResponse = new APIResponse();
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found !")
        );
        userRepository.deleteById(id);

        apiResponse.setStatusCode(200L);
        apiResponse.setMessage("Delete user success !");
        apiResponse.setData(user);
        apiResponse.setTimestamp(LocalDateTime.now());

        return apiResponse;
    }
}
