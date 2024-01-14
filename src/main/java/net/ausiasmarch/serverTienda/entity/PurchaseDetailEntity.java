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

@Entity
@Table(name = "purchaseDetail")
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "idOrdering")
    private OrderingEntity ordering;

    public PurchaseDetailEntity() {
    }

    public PurchaseDetailEntity(Long id, int quantity, Double price, ProductEntity product, OrderingEntity ordering) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.ordering = ordering;
    }

    public PurchaseDetailEntity(int quantity, Double price, ProductEntity product, OrderingEntity ordering) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.ordering = ordering;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double price() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductEntity product() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrderingEntity ordering() {
        return ordering;
    }

    public void setOrdering(OrderingEntity ordering) {
        this.ordering = ordering;
    }
    
}
