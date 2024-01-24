/*
   Custom exception for cases where an operation cannot be performed.
*/
package net.ausiasmarch.serverTienda.exception;

public class CannotPerformOperationException extends RuntimeException {

    /*
     * Constructs a CannotPerformOperationException with a specified error message.
     * 
     * @param msg Error message indicating the reason for the exception.
     */
    public CannotPerformOperationException(String msg) {
        super("ERROR: Cannot perform operation: " + msg);
    }

}
