package com.TrungTinhBackend.Day_2_Springboot.exception;

import com.TrungTinhBackend.Day_2_Springboot.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse> handelExceptionHandler(RuntimeException ex) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatusCode(500L);
        apiResponse.setMessage("Message " + ex.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
