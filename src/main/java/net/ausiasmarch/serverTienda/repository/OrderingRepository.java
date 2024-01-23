package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.OrderingEntity;

public interface OrderingRepository extends JpaRepository<OrderingEntity, Long> {

    // Find orders by user Id
    Page<OrderingEntity> findByUserId (Long user_id, Pageable oPageable);

    // Find orders by date order desc
    @Query(value = "SELECT * FROM ordering WHERE date_order DESC", nativeQuery = true)
    Page<OrderingEntity> findOrderingBydate_orderDesc(Pageable pageable);

    // Find orders by date order asc
    @Query(value = "SELECT * FROM ordering WHERE date_order ASC", nativeQuery = true)
    Page<OrderingEntity> findOrderingBydate_orderAsc(Pageable pageable);

    // Find orders by date order containing
    @Query(value = "SELECT * FROM ordering WHERE date_order LIKE %?1%", nativeQuery = true)
    Page<OrderingEntity> findOrderingBydate_orderContaining(String date_order, Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
