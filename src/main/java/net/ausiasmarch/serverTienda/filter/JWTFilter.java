/*
   JWTFilter class for filtering and validating JWT (JSON Web Token) in HTTP requests.
*/
package net.ausiasmarch.serverTienda.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import net.ausiasmarch.serverTienda.helper.JWTHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class JWTFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String auth = request.getHeader("Authorization");

            if (auth != null && auth.startsWith("Bearer ")) {
                String token = auth.substring(7);
                String username = JWTHelper.validateJWT(token);
                if (username != null) {
                    request.setAttribute("username", username);
                } 
            }
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
    
}
