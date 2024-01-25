/*
   Entity class representing an Order with attributes, 
   constraints, and relationships.
*/
package net.ausiasmarch.serverTienda.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date_order;

    @NotNull
    private Long num_bill;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date_bill;

    @JsonBackReference("user-order")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    @JsonManagedReference("order-purchasedetail")
    @OneToMany(mappedBy = "order", fetch = jakarta.persistence.FetchType.LAZY)
    private List<PurchaseDetailEntity> purchasesdetails;

    /*
     * Default constructor
     */
    public PurchaseEntity() {
    }

    /*
     * Constructor with parameters for full entity initialization.
     * 
     * @param id          Order's ID.
     * @param date_order  Order's date of order.
     * @param num_bill    Order's bill number.
     * @param date_bill   Order's date of bill.
     * @param user_id     User associated with the order.
     */
    public PurchaseEntity(Long id, LocalDate date_order, Long num_bill, LocalDate date_bill, UserEntity user_id) {
        this.id = id;
        this.date_order = date_order;
        this.num_bill = num_bill;
        this.date_bill = date_bill;
        this.user_id = user_id;
    }

    /*
     * Constructor with parameters for partial entity initialization.
     * 
     * @param date_order  Order's date of order.
     * @param num_bill    Order's bill number.
     * @param date_bill   Order's date of bill.
     * @param user_id     User associated with the order.
     */
    public PurchaseEntity(LocalDate date_order,  Long num_bill, LocalDate date_bill, UserEntity user_id) {
        this.date_order = date_order;
        this.num_bill = num_bill;
        this.date_bill = date_bill;
        this.user_id = user_id;
    }

    /*
     * Get the order's ID.
     * 
     * @return Order's ID.
     */
    public Long getId() {
        return id;
    }

    /*
     * Set the order's ID.
     * 
     * @param id Order's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Get the order's date of order.
     * 
     * @return Order's date of order.
     */
    public LocalDate getDate_order() {
        return date_order;
    }

    /*
     * Set the order's date of order.
     * 
     * @param date_order Order's date of order.
     */
    public void setDate_order(LocalDate date_order) {
        this.date_order = date_order;
    }

    /*
     * Get the order's bill number.
     * 
     * @return Order's bill number.
     */
    public Long getNum_bill() {
        return num_bill;
    }

    /*
     * Set the order's bill number.
     * 
     * @param num_bill Order's bill number.
     */
    public void setNum_bill(Long num_bill) {
        this.num_bill = num_bill;
    }

    /*
     * Get the order's date of bill.
     * 
     * @return Order's date of bill.
     */
    public LocalDate getDate_bill() {
        return date_bill;
    }

    /*
     * Set the order's date of bill.
     * 
     * @param date_bill Order's date of bill.
     */
    public void setDate_bill(LocalDate date_bill) {
        this.date_bill = date_bill;
    }

    /*
     * Get the user associated with the order.
     * 
     * @return User associated with the order.
     */
    public UserEntity getUser() {
        return user_id;
    }

    /*
     * Set the user associated with the order.
     * 
     * @param user_id User associated with the order.
     */
    public void setUser(UserEntity user_id) {
        this.user_id = user_id;
    }
    
}
