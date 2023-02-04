package com.microservice.lab.configuration.initalizetoken;

import antlr.Token;
import com.microservice.lab.configuration.data.UserDetailsServiceImpl;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.repository.TemporaryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenHeader(request);
            if (token != null) {
                boolean isNotExpired = tokenProvider.validateJwtToken(token);
                if (isNotExpired) {
                    String email = refreshToken(token).getUser().getEmail();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }
    private String getTokenHeader(HttpServletRequest request) {
        String token = request.getHeader("x-token-value");
        if (token != null && token.startsWith("Microservice ")) {
            return token.replace("Microservice ", "");
        }
        return null;
    }

    private TokenTemporary refreshToken(String token) {
        return tokenProvider.reverseToken(token);
    }
}
