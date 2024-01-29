/*
   PurchaseRepository interface for managing PurchaseEntity persistence.
   JpaRepository provides standard CRUD operations and queries for the PurchaseEntity entity.
   Additional custom queries for specific operations on the PurchaseEntity table.
*/
package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    // Find purchases by user Id
    Page<PurchaseEntity> findByUserId (Long user_id, Pageable oPageable);

    // Find purchases by date order desc
    @Query(value = "SELECT * FROM purchase WHERE date_purchase DESC", nativeQuery = true)
    Page<PurchaseEntity> findPurchaseByDateOrderDesc(Pageable pageable);

    // Find purchases by date order asc
    @Query(value = "SELECT * FROM purchase WHERE date_purchase ASC", nativeQuery = true)
    Page<PurchaseEntity> findPurchaseByDateOrderAsc(Pageable pageable);

    // Find purchases by date order containing
    @Query(value = "SELECT * FROM purchase WHERE date_purchase LIKE %?1%", nativeQuery = true)
    Page<PurchaseEntity> findPurchaseByDateOrderContaining(String date_purchase, Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
