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

    /*
     * Initializes the filter.
     * 
     * @param filterConfig Filter configuration object.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
     * Performs filtering of the incoming HTTP requests.
     * 
     * @param servletRequest  The ServletRequest object.
     * @param servletResponse The ServletResponse object.
     * @param filterChain     The FilterChain object for executing the filter chain.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest oRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse oResponse = (HttpServletResponse) servletResponse;

        // Handling OPTIONS requests
        if ("OPTIONS".equals(oRequest.getMethod())) {
            oResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Extracting JWT token from Authorization header
            String auth = oRequest.getHeader("Authorization");

            if (auth != null && auth.startsWith("Bearer ")) {
                String token = auth.substring(7);
                // Validating the JWT token
                String username = JWTHelper.validateJWT(token);
                if (username != null) {
                    // Setting the username attribute in the request
                    oRequest.setAttribute("username", username);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*
     * Destroys the filter.
     */
    @Override
    public void destroy() {
    }
    
}
