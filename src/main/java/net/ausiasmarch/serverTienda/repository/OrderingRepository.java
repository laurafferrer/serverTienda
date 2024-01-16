package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.OrderingEntity;

public interface OrderingRepository extends JpaRepository<OrderingEntity, Long> {

    // Find orders by user Id
    Page<OrderingEntity> findByIdUser (Long idUser, Pageable oPageable);

    // Find orders by date order desc
    @Query(value = "SELECT * FROM ordering WHERE dateOrder DESC", nativeQuery = true)
    Page<OrderingEntity> findOrderingByDateOrderDesc(Pageable pageable);

    // Find orders by date order asc
    @Query(value = "SELECT * FROM ordering WHERE dateOrder ASC", nativeQuery = true)
    Page<OrderingEntity> findOrderingByDateOrderAsc(Pageable pageable);

    // Find orders by date order containing
    @Query(value = "SELECT * FROM ordering WHERE dateOrder LIKE %?1%", nativeQuery = true)
    Page<OrderingEntity> findOrderingByDateOrderContaining(String dateOrder, Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
