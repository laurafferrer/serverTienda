/*
   Bean class representing a CAPTCHA entity.
   It holds fields for username, password, token, and answer.
*/
package net.ausiasmarch.serverTienda.bean;

public class CaptchaBean {

    private String username = "";
    private String password = "";
    private String token = "";
    private String answer = "";

    /*
     * Getter method for retrieving the username.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Setter method for setting the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * Getter method for retrieving the password.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Setter method for setting the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Getter method for retrieving the token.
     */
    public String getToken() {
        return token;
    }

    /*
     * Setter method for setting the token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /*
     * Getter method for retrieving the answer.
     */
    public String getAnswer() {
        return answer;
    }

    /*
     * Setter method for setting the answer.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
