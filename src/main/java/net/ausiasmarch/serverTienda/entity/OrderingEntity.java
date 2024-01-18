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

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ordering")
public class OrderingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOrder;

    @NotNull
    private Long numBill;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateBill;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity idUser;

    public OrderingEntity() {
    }

    public OrderingEntity(Long id, LocalDate dateOrder, Long numBill, LocalDate dateBill, UserEntity idUser) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.numBill = numBill;
        this.dateBill = dateBill;
        this.idUser = idUser;
    }

    public OrderingEntity(LocalDate dateOrder,  Long numBill, LocalDate dateBill, UserEntity idUser) {
        this.dateOrder = dateOrder;
        this.numBill = numBill;
        this.dateBill = dateBill;
        this.idUser = idUser;
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

    public Long getNumBill() {
        return numBill;
    }

    public void setNumBill(Long numBill) {
        this.numBill = numBill;
    }

    public LocalDate getDateBill() {
        return dateBill;
    }

    public void setDateBill(LocalDate dateBill) {
        this.dateBill = dateBill;
    }

    public UserEntity getUser() {
        return idUser;
    }

    public void setUser(UserEntity idUser) {
        this.idUser = idUser;
    }
    
}
