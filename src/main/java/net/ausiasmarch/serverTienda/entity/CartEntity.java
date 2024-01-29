/*
   Entity class representing a Cart with attributes, constraints, 
   and relationships to User and Product entities.
*/
package net.ausiasmarch.serverTienda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cart")
public class CartEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount cannot be null")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    /*
     * Default constructor.
     */
    public CartEntity() {
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id      Cart's ID.
     * @param amount  Cart's amount.
     * @param user    User associated with the cart.
     * @param product Product associated with the cart.
     */
    public CartEntity(Long id, int amount, UserEntity user, ProductEntity product) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param amount  Cart's amount.
     * @param user    User associated with the cart.
     * @param product Product associated with the cart.
     */
    public CartEntity(int amount, UserEntity user, ProductEntity product) {
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    /*
     * Get the cart's ID.
     * 
     * @return Cart's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the cart's ID.
     * 
     * @param id Cart's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /*
     * Get the cart's amount.
     * 
     * @return Cart's amount.
     */
    public int getAmount() {
        return amount;
    }

    /*
     * Set the cart's amount.
     * 
     * @param amount Cart's amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /*
     * Get the user associated with the cart.
     * 
     * @return User associated with the cart.
     */
    public UserEntity getUser() {
        return user;
    }

    /*
     * Set the user associated with the cart.
     * 
     * @param user User associated with the cart.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /*
     * Get the product associated with the cart.
     * 
     * @return Product associated with the cart.
     */
    public ProductEntity getProduct() {
        return product;
    }

    /*
     * Set the product associated with the cart.
     * 
     * @param product Product associated with the cart.
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}
