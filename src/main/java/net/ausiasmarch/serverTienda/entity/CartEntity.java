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
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    public CartEntity() {
    }

    public CartEntity(Long id, int amount, UserEntity user, ProductEntity product) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    public CartEntity(int amount, UserEntity user, ProductEntity product) {
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    public CartEntity(UserEntity user, ProductEntity product) {
        this.user = user;
        this.product = product;
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
        return user;
    }

    public void setIdUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getIdProduct() {
        return product;
    }

    public void setIdProduct(ProductEntity product) {
        this.product = product;
    }

}
