package com.example.demo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod().toUpperCase();

        System.out.println("🔍 Request path: " + path + " | Method: " + method);

        if (isPublicEndpoint(path, method)) {
            System.out.println("✅ Public endpoint. Skipping authentication.");
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.length() > 15) {
            System.out.println("🛡️ Token header: " + authHeader.substring(0, 10) + "... (masked)");
        } else {
            System.out.println("🛡️ Token header: null or too short");
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or Malformed Authorization Header");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or Expired Token");
            return;
        }

        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid User in Token");
            return;
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

private boolean isPublicEndpoint(String path, String method) {
    return path.equals("/error") ||
            ((path.equals("/auth/login") || path.equals("/authenticate")) && method.equals("POST")) ||
            (path.equals("/addCustomer") && method.equals("POST")) ||
            (path.equals("/addVendor") && method.equals("POST")) ||
            (path.equals("/addRestaurant") && method.equals("POST")) ||     // ✅ Add this line
            (path.equals("/showRestaurants") && method.equals("GET")) ||
            (path.matches("/searchRestaurantById/\\d+") && method.equals("GET")) ||
            (path.matches("/searchRestaurantByName/.+") && method.equals("GET")) ||
            (path.matches("/searchRestaurantByCity/.+") && method.equals("GET")) ||
            (path.matches("/searchRestaurantByNameAndLocation/.+/.+") && method.equals("GET")) ||
            (path.equals("/showMenu") && method.equals("GET")) ||
            (path.matches("/searchMenuById/\\d+") && method.equals("GET")) ||
            (path.matches("/searchMenuBySpeciality/.+") && method.equals("GET")) ||
            (path.matches("/searchMenuByRestaurant/\\d+") && method.equals("GET"));
}

}
