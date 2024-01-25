/*
   Entity class representing a PurchaseDetail with attributes, constraints, and relationships.
*/
package net.ausiasmarch.serverTienda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "purchaseDetail")
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    private Double price;

    @JsonBackReference("product-purchasedetail")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @JsonBackReference("purchase-purchasedetail")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    /*
     * Default constructor.
     */
    public PurchaseDetailEntity() {
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id        Purchase detail's ID.
     * @param amount  Purchase detail's amount.
     * @param price     Purchase detail's price.
     * @param product   The product associated with the Purchase detail.
     * @param purchase  The purchase associated with the Purchase detail.
     */
    public PurchaseDetailEntity(Long id, int amount, Double price, ProductEntity product, PurchaseEntity purchase) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.purchase = purchase;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param amount    Purchase detail's amount.
     * @param price     Purchase detail's price.
     * @param product   The product associated with the Purchase detail.
     * @param purchase  The purchase associated with the Purchase detail.
     */
    public PurchaseDetailEntity(int amount, Double price, ProductEntity product, PurchaseEntity purchase) {
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.purchase = purchase;
    }

    /*
     * Get Purchase detail's ID.
     * 
     * @return Purchase detail's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set Purchase detail's ID.
     * 
     * @param id    Purchase detail's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Get Purchase detail's amount.
     * 
     * @return Purchase detail's amount.
     */
    public int getAmount() {
        return amount;
    }

    /*
     * Set Purchase detail's amount.
     * 
     * @param amount    Purchase detail's amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /*
     * Get Purchase detail's price.
     * 
     * @return Purchase detail's price.
     */
    public Double getPrice() {
        return price;
    }

    /*
     * Set Purchase detail's price.
     * 
     * @param price Purchase detail's price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /*
     * Get product associated with Purchase detail.
     * 
     * @return product associates with Purchase detail.
     */
    public ProductEntity getProduct() {
        return product;
    }

    /*
     * Set product associated with Purchase detail.
     * 
     * @param product   Product associated with Purchase detail.
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /*
     * Get purchase associated with Purchase detail.
     * 
     * @return purchase associated with Purchase detail.
     */
    public PurchaseEntity getPurchase() {
        return purchase;
    }

    /*
     * Set purchase associated with Purchase detail.
     * 
     * @return purchase associated with Purchase detail.
     */
    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }
}
