package net.ausiasmarch.serverTienda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private CategoryEntity category;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, Double price, int stock, byte[] image, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    public ProductEntity(String name, String description, Double price, int stock, byte[] image, CategoryEntity category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    public ProductEntity(String name, String description, Double price, int stock, byte[] image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double price() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int stock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public byte[] image() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
