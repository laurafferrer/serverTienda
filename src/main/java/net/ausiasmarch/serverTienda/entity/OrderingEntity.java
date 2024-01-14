package net.ausiasmarch.serverTienda.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordering")
public class OrderingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOrder;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idPurchase")
    private PurchaseDetailEntity purchase;

    public OrderingEntity() {
    }

    public OrderingEntity(Long id, LocalDate dateOrder, UserEntity user, PurchaseDetailEntity purchase) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.user = user;
        this.purchase = purchase;
    }

    public OrderingEntity(LocalDate dateOrder, UserEntity user, PurchaseDetailEntity purchase) {
        this.dateOrder = dateOrder;
        this.user = user;
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PurchaseDetailEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseDetailEntity purchase) {
        this.purchase = purchase;
    }
    
}
