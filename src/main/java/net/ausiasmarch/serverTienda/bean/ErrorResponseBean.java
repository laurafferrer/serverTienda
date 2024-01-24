/*
   Bean class representing an error response.
*/
package net.ausiasmarch.serverTienda.bean;

import java.util.Date;

public class ErrorResponseBean {

    private Date timestamp;
    private String status;
    private String message;
    private String details;

    /*
     * Constructor to initialize the ErrorResponseBean with the specified parameters.
     * 
     * @param timestamp Timestamp of the error response.
     * @param status HTTP status of the error.
     * @param message Error message.
     * @param details Additional details about the error.
     */
    public ErrorResponseBean(Date timestamp, String status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    /*
     * Get the timestamp of the error response.
     * 
     * @return Timestamp as a Date object.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /*
     * Set the timestamp of the error response.
     * 
     * @param timestamp Timestamp to be set.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /*
     * Get the HTTP status of the error.
     * 
     * @return HTTP status as a string.
     */
    public String getStatus() {
        return status;
    }

    /*
     * Set the HTTP status of the error.
     * 
     * @param status HTTP status to be set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * Get the error message.
     * 
     * @return Error message as a string.
     */
    public String getMessage() {
        return message;
    }

    /*
     * Set the error message.
     * 
     * @param message Error message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * Get additional details about the error.
     * 
     * @return Details of the error as a string.
     */
    public String getDetails() {
        return details;
    }

    /*
     * Set additional details about the error.
     * 
     * @param details Details of the error to be set.
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
}
