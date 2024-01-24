/*
   CartRepository interface for managing CartEntity persistence.

   JpaRepository provides standard CRUD operations and queries for the CartEntity entity.

   Additional custom queries for specific operations on the CartEntity table.
*/
package net.ausiasmarch.serverTienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get page of cart for a specific user
    List<CartEntity> findByUserId(Long userId);

    // Find specific item in the cart based on user Id and product Id
    Optional<CartEntity> findByUserIdAndProductId(Long userId, Long productId);

    // Remove all cart for a specific user
    @Query(value = "DELETE FROM cart WHERE user_id = ?1", nativeQuery = true)
    void deleteByUserId(Long userId);

    // Find all carts for a specific user
    @Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
    List<CartEntity> findAllByUserId(Long userId);

    // Method to reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
