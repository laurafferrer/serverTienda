/*
   Entity class representing a Category with attributes and constraints.
*/
package net.ausiasmarch.serverTienda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class CategoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String category;

    /*
     * Default constructor.
     */
    public CategoryEntity() {
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id       Category's ID.
     * @param category Category's name.
     */
    public CategoryEntity(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param category Category's name.
     */
    public CategoryEntity(String category) {
        this.category = category;
    }

    /*
     * Get the category's ID.
     * 
     * @return Category's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the category's ID.
     * 
     * @param id Category's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Get the category's name.
     * 
     * @return Category's name.
     */
    public String getCategory() {
        return category;
    }

    /*
     * Set the category's name.
     * 
     * @param category Category's name.
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
