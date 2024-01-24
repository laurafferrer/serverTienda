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
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date_order;

    @NotNull
    private Long num_bill;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date_bill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    public OrderEntity() {
    }

    public OrderEntity(Long id, LocalDate date_order, Long num_bill, LocalDate date_bill, UserEntity user_id) {
        this.id = id;
        this.date_order = date_order;
        this.num_bill = num_bill;
        this.date_bill = date_bill;
        this.user_id = user_id;
    }

    public OrderEntity(LocalDate date_order,  Long num_bill, LocalDate date_bill, UserEntity user_id) {
        this.date_order = date_order;
        this.num_bill = num_bill;
        this.date_bill = date_bill;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getdate_order() {
        return date_order;
    }

    public void setdate_order(LocalDate date_order) {
        this.date_order = date_order;
    }

    public Long getnum_bill() {
        return num_bill;
    }

    public void setnum_bill(Long num_bill) {
        this.num_bill = num_bill;
    }

    public LocalDate getdate_bill() {
        return date_bill;
    }

    public void setdate_bill(LocalDate date_bill) {
        this.date_bill = date_bill;
    }

    public UserEntity getUser() {
        return user_id;
    }

    public void setUser(UserEntity user_id) {
        this.user_id = user_id;
    }
    
}
