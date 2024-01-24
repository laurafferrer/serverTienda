/*
   Entity class representing a Captcha with attributes, constraints, 
   and relationships to Pendent entities.
*/
package net.ausiasmarch.serverTienda.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "captcha")
public class CaptchaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String text;

    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "captcha", fetch = FetchType.LAZY)
    private List<PendentEntity> pendents;

     /*
     * Default constructor initializes the list of pendents.
     */
    public CaptchaEntity() {
        pendents = new ArrayList<>();
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id    Captcha's ID.
     * @param text  Text associated with the captcha.
     * @param image Image data for the captcha.
     */
    public CaptchaEntity(Long id, String text, byte[] image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param text  Text associated with the captcha.
     * @param image Image data for the captcha.
     */
    public CaptchaEntity(String text, byte[] image) {
        this.text = text;
        this.image = image;
    }

    /*
     * Get the captcha's ID.
     * 
     * @return Captcha's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the captcha's ID.
     * 
     * @param id Captcha's ID.
     */
    public void setId(Long id) {
        this.id = id;        
    }

    /*
     * Get the text associated with the captcha.
     * 
     * @return Text associated with the captcha.
     */
    public String getText() {
        return text;
    }

    /*
     * Set the text associated with the captcha.
     * 
     * @param text Text associated with the captcha.
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * Get the image data for the captcha.
     * 
     * @return Image data for the captcha.
     */
    public byte[] getImage() {
        return image;
    }

    /*
     * Set the image data for the captcha.
     * 
     * @param image Image data for the captcha.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}
