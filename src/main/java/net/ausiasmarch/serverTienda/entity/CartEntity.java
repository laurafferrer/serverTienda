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
    @JoinColumn(name = "idUser")
    private UserEntity idUser;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity idProduct;

    public CartEntity() {
    }

    public CartEntity(Long id, int amount, UserEntity idUser, ProductEntity idProduct) {
        this.id = id;
        this.amount = amount;
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public CartEntity(int amount, UserEntity idUser, ProductEntity idProduct) {
        this.amount = amount;
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public CartEntity(UserEntity idUser, ProductEntity idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    public ProductEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductEntity idProduct) {
        this.idProduct = idProduct;
    }

}
