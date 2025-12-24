package com.holis.san01.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtGenerator tokenGenerator;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthFilter(
            JwtGenerator tokenGenerator,
            JwtTokenUtil jwtTokenUtil,
            CustomUserDetailsService customUserDetailsService) {
        this.tokenGenerator = tokenGenerator;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/refreshtoken")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getJWTFromRequest(request);

        // Recebeu o token, validar o token e verificar se o usuário está ok
        if (StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
            String email = tokenGenerator.getEmailFromJWT(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            jwtTokenUtil.loadTokenData(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstants.JWT_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
