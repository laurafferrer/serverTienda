package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {

    Optional<PurchaseDetailEntity> findById(Long id);

    @Query(value = "SELECT * FROM purchaseDetail WHERE price LIKE %?1%", nativeQuery = true)
    Optional<PurchaseDetailEntity> findByPrice(String price);

    @Query(value = "SELECT * FROM purchaseDetail WHERE quantity LIKE %?1%", nativeQuery = true)
    Optional<PurchaseDetailEntity> findByQuantity(String quantity);

    @Modifying
    @Query(value = "ALTER TABLE purchaseDetail AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
