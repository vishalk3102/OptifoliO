package com.optifolio.security;

import com.optifolio.repositories.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtHelper jwtHelper;

    @Autowired
    private  TokenBlacklist tokenBlacklist;

    @Autowired
    private  UserRepository usersRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    //FUNCTION TO FILTER EACH INCOMING HTTP REQUEST TO VALIDATE JWT TOKEN
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            log.debug("JWT token extracted: {}", token);
            log.debug("Authorization header detected: {}", requestHeader);

            try {
                username = jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                log.warn("JWT token has expired", e);
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                log.warn("Invalid JWT token", e);
                e.printStackTrace();
            } catch (Exception e) {
                log.error("Unable to process JWT token", e);
                e.printStackTrace();
            }
        } else {
            log.info("Invalid Header Value");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation Fails");
            }

        }
        filterChain.doFilter(request, response);
    }
}
