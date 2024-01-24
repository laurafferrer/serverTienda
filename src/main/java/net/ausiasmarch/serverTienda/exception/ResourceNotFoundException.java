/*
   Custom exception for cases where a requested resource is not found.
*/
package net.ausiasmarch.serverTienda.exception;

public class ResourceNotFoundException extends RuntimeException {

    /*
     * Constructs a ResourceNotFoundException with a specified error message.
     * 
     * @param message Error message indicating the reason for the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }   

}
