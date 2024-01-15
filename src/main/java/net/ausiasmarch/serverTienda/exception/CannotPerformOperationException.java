package net.ausiasmarch.serverTienda.exception;

// PARA EL POSIBLE CAPTCHA. NO BORRAR
public class CannotPerformOperationException extends RuntimeException {

    public CannotPerformOperationException(String msg) {
        super("ERROR: Cannot perform operation: " + msg);
    }

}
