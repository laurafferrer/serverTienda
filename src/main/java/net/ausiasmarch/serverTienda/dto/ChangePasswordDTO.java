package net.ausiasmarch.serverTienda.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for changing user password.
 */
public class ChangePasswordDTO {

    /**
     * The new password.
     * 
     * @return The new password.
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * The confirmation of the new password.
     * 
     * @return The confirmation of the new password.
     */
    @NotBlank(message = "Confirm password")
    private String confirmPassword;

    /**
     * The token associated with the password change request.
     * 
     * @return The token associated with the password change request.
     */
    @NotBlank(message = "tokenPassword is required")
    private String tokenPassword;

    /**
     * Default constructor for ChangePasswordDTO.
     */
    public ChangePasswordDTO() {
    }

    /**
     * Constructor for ChangePasswordDTO with parameters.
     * 
     * @param password        The new password.
     * @param confirmPassword The confirmation of the new password.
     * @param tokenPassword   The token associated with the password change request.
     */
    public ChangePasswordDTO(String password, String confirmPassword, String tokenPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.tokenPassword = tokenPassword;
    }

    /**
     * Getter for the new password.
     * 
     * @return The new password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for the new password.
     * 
     * @param strPassword The new password.
     */
    public void setPassword(String strPassword) {
        this.password = strPassword;
    }

    /**
     * Getter for the confirmation of the new password.
     * 
     * @return The confirmation of the new password.
     */
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    /**
     * Setter for the confirmation of the new password.
     * 
     * @param strConfirmPassword The confirmation of the new password.
     */
    public void setConfirmPassword(String strConfirmPassword) {
        this.confirmPassword = strConfirmPassword;
    }

    /**
     * Getter for the token associated with the password change request.
     * 
     * @return The token associated with the password change request.
     */
    public String getTokenPassword() {
        return this.tokenPassword;
    }

    /**
     * Setter for the token associated with the password change request.
     * 
     * @param strTokenPassword The token associated with the password change request.
     */
    public void setTokenPassword(String strTokenPassword) {
        this.tokenPassword = strTokenPassword;
    }
}
