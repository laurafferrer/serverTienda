/*
   Entity class representing a product with its 
   attributes and relationships.
*/
package net.ausiasmarch.serverTienda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private int stock;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    /*
     * Default constructor initializes the entity with null values.
     */
    public ProductEntity() {
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id            Product ID.
     * @param name          Product name.
     * @param description   Product description.
     * @param price         Product price.
     * @param stock         Product stock quantity.
     * @param image         Product image.
     * @param category      Product category.
     */
    public ProductEntity(Long id, String name, String description, Double price, int stock, String image, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param name          Product name.
     * @param description   Product description.
     * @param price         Product price.
     * @param stock         Product stock quantity.
     * @param image         Product image.
     * @param category      Product category.
     */
    public ProductEntity(String name, String description, Double price, int stock, String image, CategoryEntity category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param name          Product name.
     * @param description   Product description.
     * @param price         Product price.
     * @param stock         Product stock quantity.
     * @param category      Product category.
     */
    public ProductEntity(String name, String description, Double price, int stock, CategoryEntity category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param name          Product name.
     * @param description   Product description.
     * @param price         Product price.
     * @param stock         Product stock quantity.
     * @param image         Product image.
     * @param category      Product category.
     */
    public ProductEntity(String name, String description, Double price, int stock, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param name          Product name.
     * @param description   Product description.
     * @param price         Product price.
     * @param stock         Product stock quantity.
     * @param image         Product image.
     * @param category      Product category.
     */
    public ProductEntity(String name, String description, Double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Gets the product ID.
     * 
     * @return The product ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     * 
     * @param id The product ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     * 
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * 
     * @param name The product name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product description.
     * 
     * @return The product description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     * 
     * @param description The product description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product price.
     * 
     * @return The product price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * 
     * @param price The product price to set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the product stock quantity.
     * 
     * @return The product stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the product stock quantity.
     * 
     * @param stock The product stock quantity to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the product image.
     * 
     * @return The product image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the product image.
     * 
     * @param image The product image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the product category.
     * 
     * @return The product category.
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     * 
     * @param category The product category to set.
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
