package net.ausiasmarch.serverTienda.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.OrderingEntity;

public interface OrderingRepository extends JpaRepository<OrderingEntity, Long> {
    
    Optional<OrderingEntity> findById(Long id);

    @Query(value = "SELECT * FROM ordering WHERE dateOrder LIKE %?1%", nativeQuery = true)
    Page<OrderingEntity> findOrderingByDateOrderContaining(LocalDate dateOrder, Pageable pageable);

    @Query(value = "SELECT d.*, count o(o.id) FROM purchaseDetail d, ordering o WHERE o.id = d.idOrdering GROUP BY o.id ORDER BY count(o.id) DESC, nativeQuery = true")
    Page<OrderingEntity> findOrderingByPurchaseDetailDesc(Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
