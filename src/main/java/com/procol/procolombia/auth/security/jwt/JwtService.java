package com.procol.procolombia.auth.security.jwt;


import com.procol.procolombia.auth.service.RolService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtService {
    private final SecretKey key;
    private final long expiration;
    private final RolService rolService;

    public JwtService(@Value("${jwt.secretKey}") String secretKey, @Value("${jwt.expiration}") long expiration, RolService rolService) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
        this.rolService = rolService;
    }

    public String generateToken(String email, List<String> roles) {
        return Jwts.builder()
                .subject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("roles", List.class);
    }


    public boolean isTokenValid(String token) {
        try{
            Jwts.parser().verifyWith(key).build().parse(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }

    public Long getExpirationTime(){
        return System.currentTimeMillis()+expiration;
    }
}
