/*
   Bean class representing a User for data transfer.
*/
package net.ausiasmarch.serverTienda.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBean {

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /*
     * Get the username of the user.
     * 
     * @return User's username as a string.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Set the username of the user.
     * 
     * @param username User's username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * Get the password of the user.
     * 
     * @return User's password as a string.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Set the password of the user.
     * 
     * @param password User's password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
