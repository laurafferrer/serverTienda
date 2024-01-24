/*
   OrderRepository interface for managing OrderEntity persistence.
   JpaRepository provides standard CRUD operations and queries for the OrderEntity entity.
   Additional custom queries for specific operations on the OrderEntity table.
*/
package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // Find orders by user Id
    Page<OrderEntity> findByUserId (Long user_id, Pageable oPageable);

    // Find orders by date order desc
    @Query(value = "SELECT * FROM order WHERE date_order DESC", nativeQuery = true)
    Page<OrderEntity> findOrderByDateOrderDesc(Pageable pageable);

    // Find orders by date order asc
    @Query(value = "SELECT * FROM order WHERE date_order ASC", nativeQuery = true)
    Page<OrderEntity> findOrderByDateOrderAsc(Pageable pageable);

    // Find orders by date order containing
    @Query(value = "SELECT * FROM order WHERE date_order LIKE %?1%", nativeQuery = true)
    Page<OrderEntity> findOrderByDateOrderContaining(String date_order, Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
