/*
   Entity class representing a Pending Entity with attributes, 
   constraints, and relationships.
*/
package net.ausiasmarch.serverTienda.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pendent")
public class PendentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "captcha_id")
    private CaptchaEntity captcha;

    private String token;

    private LocalDateTime timecode;

    /*
     * Get the ID of the pending entity.
     * 
     * @return ID of the pending entity.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the ID of the pending entity.
     * 
     * @param id ID of the pending entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Get the CaptchaEntity associated with the pending entity.
     * 
     * @return CaptchaEntity associated with the pending entity.
     */
    public CaptchaEntity getCaptcha() {
        return captcha;
    }

    /*
     * Set the CaptchaEntity associated with the pending entity.
     * 
     * @param captcha CaptchaEntity associated with the pending entity.
     */
    public void setCaptcha(CaptchaEntity captcha) {
        this.captcha = captcha;
    }

    /*
     * Get the token of the pending entity.
     * 
     * @return Token of the pending entity.
     */
    public String getToken() {
        return token;
    }

    /*
     * Set the token of the pending entity.
     * 
     * @param token Token of the pending entity.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /*
     * Get the timecode of the pending entity.
     * 
     * @return Timecode of the pending entity.
     */
    public LocalDateTime getTimecode() {
        return timecode;
    }

    /*
     * Set the timecode of the pending entity.
     * 
     * @param timecode Timecode of the pending entity.
     */
    public void setTimecode(LocalDateTime timecode) {
        this.timecode = timecode;
    }
}
