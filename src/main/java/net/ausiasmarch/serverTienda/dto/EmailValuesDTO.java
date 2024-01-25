package net.ausiasmarch.serverTienda.dto;

/**
 * Data Transfer Object (DTO) for storing email-related values.
 */
public class EmailValuesDTO {

    /**
     * Sender's email address.
     * 
     * @return The sender's email address.
     */
    private String mailFrom;

    /**
     * Recipient's email address.
     * 
     * @return The recipient's email address.
     */
    private String mailTo;

    /**
     * Email subject.
     * 
     * @return The email subject.
     */
    private String mailSubject;

    /**
     * User's name associated with the email.
     * 
     * @return The user's name.
     */
    private String userName;

    /**
     * Token associated with password reset.
     * 
     * @return The token for password reset.
     */
    private String tokenPassword;

    /**
     * Default constructor for EmailValuesDTO.
     */
    public EmailValuesDTO() {
    }

    /**
     * Constructor for EmailValuesDTO with parameters.
     * 
     * @param mailFrom      Sender's email address.
     * @param mailTo        Recipient's email address.
     * @param mailSubject   Email subject.
     * @param userName      User's name associated with the email.
     * @param tokenPassword Token associated with password reset.
     */
    public EmailValuesDTO(String mailFrom, String mailTo, String mailSubject, String userName, String tokenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.mailSubject = mailSubject;
        this.userName = userName;
        this.tokenPassword = tokenPassword;
    }
    
    /**
     * Getter for the sender's email address.
     * 
     * @return The sender's email address.
     */
    public String getMailFrom() {
        return this.mailFrom;
    }

    /**
     * Setter for the sender's email address.
     * 
     * @param mailFrom The sender's email address.
     */
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    /**
     * Getter for the recipient's email address.
     * 
     * @return The recipient's email address.
     */
    public String getMailTo() {
        return this.mailTo;
    }

    /**
     * Setter for the recipient's email address.
     * 
     * @param mailTo The recipient's email address.
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * Getter for the email subject.
     * 
     * @return The email subject.
     */
    public String getMailSubject() {
        return this.mailSubject;
    }

    /**
     * Setter for the email subject.
     * 
     * @param mailSubject The email subject.
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * Getter for the user's name.
     * 
     * @return The user's name.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Setter for the user's name.
     * 
     * @param userName The user's name.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the token associated with password reset.
     * 
     * @return The token for password reset.
     */
    public String getTokenPassword() {
        return this.tokenPassword;
    }

    /**
     * Setter for the token associated with password reset.
     * 
     * @param tokenPassword The token for password reset.
     */
    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
}
