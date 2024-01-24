/*
   Bean class representing the response for CAPTCHA generation.
*/
package net.ausiasmarch.serverTienda.bean;

public class CaptchaResponseBean {

    private String token;
    private byte[] captchaImage;

    /*
     * Get the Captcha token.
     * 
     * @return Captcha token as a String.
     */
    public String getToken() {
        return token;
    }

    /*
     * Set the Captcha token.
     * 
     * @param token Captcha token to be set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /*
     * Get the byte array representing the Captcha image.
     * 
     * @return Byte array representing the Captcha image.
     */
    public byte[] getCaptchaImage() {
        return captchaImage;
    }

    /*
     * Set the byte representing the Captcha image.
     * 
     * @param captchaImage Byte array representing the Captcha image to be set.
     */
    public void setCaptchaImage(byte[] captchaImage) {
        this.captchaImage = captchaImage;
    }
    
}
