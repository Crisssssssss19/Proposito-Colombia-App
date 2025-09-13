package com.procol.procolombia.auth.security.jwt;

import com.procol.procolombia.auth.security.service.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private static final Logger loger = LoggerFactory.getLogger(JwtFilter.class);
    public JwtFilter(JwtService jwtService, UserInfoService userInfoService) {
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                String email = jwtService.extractEmail(jwtToken);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userInfoService.loadUserByUsername(email);

                    if (jwtService.isTokenValid(jwtToken)) {
                        List<String> roles = jwtService.extractRoles(jwtToken);

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        roles.stream()
                                                .map(String::trim)
                                                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)  // si ya tiene ROLE_, no lo duplicamos
                                                .map(SimpleGrantedAuthority::new)
                                                .toList()

                                );

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }



                }
            }
        } catch (Exception e) {
            logger.error("Error en autenticacion con JWT", e);
        }
        filterChain.doFilter(request, response);
    }
}
