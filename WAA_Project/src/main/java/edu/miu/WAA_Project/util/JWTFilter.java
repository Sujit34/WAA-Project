package edu.miu.WAA_Project.util;

import edu.miu.WAA_Project.exceptions.UserDeactivatedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JWTFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String requestMethod = request.getMethod();
        String requestPath = request.getServletPath();
        boolean isRefreshRequest = requestMethod.equals("POST") && requestPath.equals("/api/v1/authenticate/refresh");
        boolean isNoAuthRequest = requestPath.startsWith("/api/v1/authenticate/") || requestPath.startsWith("/api/v1/properties");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            if (!isNoAuthRequest) {
                response.sendError(401);
                filterChain.doFilter(request, response);
                return;
            }
        } else {
            String token = authorizationHeader.substring(7);
            String username = null;

            try {
                username = jwtUtil.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) {
                if (!isRefreshRequest) {
                    response.sendError(401, "TOKEN_EXPIRED");
                    filterChain.doFilter(request, response);
                    return;
                }
            } catch (JwtException e) {
                System.out.println(e.getMessage());
                response.sendError(401);
                filterChain.doFilter(request, response);
                return;
            }


            UserDetails userDetails = null;

            try {

                userDetails = userDetailsService.loadUserByUsername(username);

            } catch (UserDeactivatedException e) {
                response.sendError(401, e.getMessage());
                filterChain.doFilter(request, response);
                return;
            }

            if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
