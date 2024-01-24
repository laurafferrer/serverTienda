/*
   Custom exception for cases where an unauthorized access attempt is detected.
*/
package net.ausiasmarch.serverTienda.exception;

public class UnauthorizedException extends RuntimeException {

    /*
     * Constructs an UnauthorizedException with a specified error message.
     * 
     * @param msg Error message indicating the reason for the exception.
     */
    public UnauthorizedException(String msg) {
        super("ERROR: Unauthorized access attempt: " + msg);
    }

}
