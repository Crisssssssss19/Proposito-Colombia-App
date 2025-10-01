package com.procol.procolombia.auth.security.jwt;

import com.procol.procolombia.auth.security.service.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        try {
            String authHeader = request.getHeader("Authorization");
            loger.trace("[{}] Auth Header: {}", requestId, authHeader != null ? "present" : "null");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                loger.debug("[{}] token (masked) = {}...", requestId, jwtToken.length()>8 ? jwtToken.substring(0, 8) : jwtToken);

                String email = jwtService.extractEmail(jwtToken);
                loger.debug("[{}] token subject/email = {}", requestId, email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userInfoService.loadUserByUsername(email);
                    loger.debug("[{}] userDetails loaded: username={}, passwordSet={}, authoritiesFromUser={}",
                            requestId, userDetails.getUsername(), userDetails.getPassword()!=null, userDetails.getAuthorities());

                    if (jwtService.isTokenValid(jwtToken)) {
                        List<String> roles = jwtService.extractRoles(jwtToken);
                        loger.debug("[{}] roles from token = {}", requestId, roles);

                        var authorities = roles.stream()
                                .map(String::trim)
                                .map(String::toUpperCase)
                                .map(SimpleGrantedAuthority::new)
                                .toList();
                        loger.debug("[{}] authorities to set = {}", requestId, authorities);

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        authorities

                                );

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        loger.info("[{}] Authentication set -> principal={}, authorities={}",
                                requestId,
                                authentication.getPrincipal(),
                                authentication.getAuthorities());
                        loger.info("[{}] Authentication set in SecurityContext for user={}", requestId, email);
                    } else {
                        loger.warn("[{}] Token invalido para email={}", requestId, email);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error en autenticacion con JWT", e);
        } finally {
            MDC.remove("requestId");
        }
        filterChain.doFilter(request, response);
    }
}
