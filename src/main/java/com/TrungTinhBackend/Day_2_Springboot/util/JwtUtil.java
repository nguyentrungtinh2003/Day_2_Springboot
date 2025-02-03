package com.TrungTinhBackend.Day_2_Springboot.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chuyển secret key từ String -> Key hợp lệ
    private Key getSigningKey() {
        String secret = "G9o5Rk+1XYq8rPZ9s3LJWZ0qMNuX+M3sL7swcT0Fz5E=";
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        int expiration = 86400000;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }
}
