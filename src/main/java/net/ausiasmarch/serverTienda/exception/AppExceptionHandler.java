/*
   Global exception handler for the application.
   Provides custom responses for specific exceptions.
*/
package net.ausiasmarch.serverTienda.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import net.ausiasmarch.serverTienda.bean.ErrorResponseBean;

public class AppExceptionHandler {

    /*
     * Handles UnauthorizedException and returns a custom response.
     * 
     * @param ex UnauthorizedException instance.
     * @param request WebRequest instance representing the current request.
     * @return ResponseEntity with the custom error response and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> UnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.UNAUTHORIZED.name(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    /*
     * Handles ResourceNotFoundException and returns a custom response.
     * 
     * @param ex ResourceNotFoundException instance.
     * @param request WebRequest instance representing the current request.
     * @return ResponseEntity with the custom error response and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.NOT_FOUND.name(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /*
     * Handles generic Exception and returns a custom response.
     * 
     * @param ex Exception instance.
     * @param request WebRequest instance representing the current request.
     * @return ResponseEntity with the custom error response and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> AppExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
