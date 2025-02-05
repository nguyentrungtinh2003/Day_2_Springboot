package com.TrungTinhBackend.Day_2_Springboot;

import com.TrungTinhBackend.Day_2_Springboot.dto.UserDTO;
import com.TrungTinhBackend.Day_2_Springboot.entity.User;
import com.TrungTinhBackend.Day_2_Springboot.repository.UserRepository;
import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;
import com.TrungTinhBackend.Day_2_Springboot.service.UserServiceImpl;
import com.TrungTinhBackend.Day_2_Springboot.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegister_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("new@email.com");
        userDTO.setUsername("newUser");
        userDTO.setPassword("password123");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        APIResponse response = userService.register(userDTO);

        assertEquals(200L, response.getStatusCode());
        assertEquals("User register success !", response.getMessage());
        assertNotNull(response.getData());
    }

    // 🟢 Test đăng nhập thành công
    @Test
    void testLogin_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("user1");
        userDTO.setPassword("correctpassword");

        User user = new User();
        user.setUsername("user1");
        user.setPassword("hashedPassword");

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(userDTO.getUsername())).thenReturn("JWT_TOKEN");

        APIResponse response = userService.login(userDTO);

        assertEquals(200L, response.getStatusCode());
        assertEquals("Login success !", response.getMessage());
        assertEquals("JWT_TOKEN", response.getData());
    }
}
