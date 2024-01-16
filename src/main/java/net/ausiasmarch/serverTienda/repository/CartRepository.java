package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get page of cart for a specific user
    Page<CartEntity> findByUser(Long idUser, Pageable oPageable);

    // Find specific item in the cart base on user Id and product Id
    Optional<CartEntity> findByUserAndIdProduct(Long idUser, Long idProduct);

    // Remove all cart items for a specific user
    @Query(value = "DELETE FROM cart WHERE idUser = ?1", nativeQuery = true)
    void deleteByIdUser(Long idUser);

    // Method to reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
