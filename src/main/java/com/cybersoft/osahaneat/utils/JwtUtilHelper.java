package com.cybersoft.osahaneat.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtUtilHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;
    public String gennerateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(privateKey));
        String jws = Jwts.builder().setSubject(data).signWith(key).compact();
        return jws;
    }

    // Kiểm tra JWT hợp lệ
    public boolean verifyJwtToken(String token) {
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(privateKey));
        try {
            Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
        return false;
    }
}
