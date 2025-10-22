package com.procol.procolombia.auth.security.jwt;


import com.procol.procolombia.auth.service.RolService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private final SecretKey key;
    private final long expiration;
    private final RolService rolService;

    public JwtService(@Value("${jwt.secretKey}") String secretKey, @Value("${jwt.expiration}") long expiration, RolService rolService) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
        this.rolService = rolService;
    }

    public String generateToken(String email, List<String> roles, Integer idUsuario, String nombreUsuario, String apellidoUsuario, String uuidAcceso) {
        logger.debug("Generating token for email={}, roles={}", email, roles);
        Date issueDate = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + expiration);

        String token = Jwts.builder()
                .subject(email)
                .claim("roles", roles)
                .claim("idUsuario", idUsuario)
                .claim("nombreUsuario", nombreUsuario)
                .claim("apellidoUsuario", apellidoUsuario)
                .claim("uuidAcceso", uuidAcceso)
                .setIssuedAt(issueDate)
                .expiration(expiresAt)
                .signWith(key)
                .compact();
        logger.info("Token generated for {} expiraEn={} ({}s)", email, expiresAt, expiration / 1000);
        logger.trace("Token (masked): {}...", token.substring(0, 12));
        return token;

    }

    public String extractEmail(String token) {
        try {
            String email = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            logger.debug("extractEmail -> {}", email);
            return email;
        } catch (Exception e) {
            logger.error("extractEmail failed: {}", e.getMessage());
            throw e;
        }
    }

    public List<String> extractRoles(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            List<String> roles = claims.get("roles", List.class);
            logger.debug("extractRoles -> {}", roles);
            return roles;
        } catch (Exception e) {
            logger.error("extractRoles failed: {}", e.getMessage());
            throw e;
        }
    }


    public boolean isTokenValid (String token){
        try {
            Jwts.parser().verifyWith(key).build().parse(token);
            logger.trace("isTokenValid -> true");
            return true;
        } catch (JwtException e) {
            logger.warn("isTokenValid -> false ({})", e.getMessage());
            return false;
        }
    }

    public Long getExpirationTime () {
        long exp = System.currentTimeMillis() + expiration;
        logger.trace("getExpirationTime -> {}", exp);
        return exp;
    }
}
