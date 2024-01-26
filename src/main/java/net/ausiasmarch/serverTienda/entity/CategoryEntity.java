/*
   Entity class representing a Category with attributes and constraints.
*/
package net.ausiasmarch.serverTienda.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String name;

    @OneToMany(mappedBy = "category", fetch = jakarta.persistence.FetchType.LAZY)
    private List<ProductEntity> products;

    /*
     * Default constructor.
     */
    public CategoryEntity() {
        products = new ArrayList<>();
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id        Category's ID.
     * @param name      Category's name.
     */
    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param name      Category's name.
     */
    public CategoryEntity(String name) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    /*
     * Set the category's name.
     * 
     * @param name Category's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Returns the number of products associated with this category.
     * 
     * @return Number of products associated with the category.
     */
    public int getProducts() {
        return products.size();
    }

}
