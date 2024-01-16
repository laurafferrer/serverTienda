package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {
/*
    // Find purchase details by id
    Optional <PurchaseDetailEntity> findById(Long id);
*/
    // Find purchase details by ordering id
    Optional <PurchaseDetailEntity> findByIdOrering(Long idOrdering, Pageable oPageable);

    // Find purchase details by product id
    Optional <PurchaseDetailEntity> findByIdProduct(Long idProduct, Pageable oPageable);

    // Find purchase details by ordering id and product id
    Optional <PurchaseDetailEntity> findByIdOreringAndIdProduct(Long idOrdering, Long idProduct, Pageable oPageable);

    // Find and order purchase details by price in descending order
    @Query(value = "SELECT * FROM purchaseDetail ORDER BY price DESC", nativeQuery = true)
    Page <PurchaseDetailEntity> findAllByPriceDesc(Pageable oPageable);

    // Find and order purchase details by price in ascending order
    @Query(value = "SELECT * FROM purchaseDetail ORDER BY price ASC", nativeQuery = true)
    Page <PurchaseDetailEntity> findAllByPriceAsc(Pageable oPageable);

    // Method to reset the auto-increment counter for the purchase detail table
    @Modifying
    @Query(value = "ALTER TABLE purchaseDetail AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
