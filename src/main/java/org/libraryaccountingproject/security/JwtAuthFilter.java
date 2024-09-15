package org.libraryaccountingproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.exeptions.InvalidJwtException;
import org.libraryaccountingproject.services.securityServices.AppUserDetailsService;
import org.libraryaccountingproject.services.securityServices.JwtProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AppUserDetailsService appUserDetailsService;
    private final JwtProvider jwtProvider;
    private final AuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (request.getRequestURI().startsWith("/swagger-ui") || request.getRequestURI().startsWith("/v3/api-docs")) {
                filterChain.doFilter(request, response);
                return;
            }
            log.info("starting jwt auth filter for uri {}", request.getRequestURI());
            String jwt = getJwtFromRequest(request);
            log.info("jwtAuthFilter jwt= {}", jwt);

            if (StringUtils.hasText(jwt) && jwtProvider.validateJwtToken(jwt)) {

                String userLogin = jwtProvider.getLoginFromJwtToken(jwt);
                log.info("JwtProvider,user login: {}", userLogin);

                UserDetails userDetails = appUserDetailsService.loadUserByUsername(userLogin);
                log.info("JwtProvider,user details: {}", userDetails.getUsername());

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                log.info("JwtProvider,authentication: {}", userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (UsernameNotFoundException e) {
            log.error("User not found", e);
            basicAuthenticationEntryPoint.commence(request, response, e);
        } catch (InvalidJwtException e) {
            log.error("Invalid JWT token", e);
            basicAuthenticationEntryPoint.commence(request, response, e);
        } catch (Exception e) {
            log.error("Unexpected error in authentication", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        filterChain.doFilter(request, response);
        log.info("finished jwtAuthFilter  {} response status: {}", request.getUserPrincipal(), response.getStatus());
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
