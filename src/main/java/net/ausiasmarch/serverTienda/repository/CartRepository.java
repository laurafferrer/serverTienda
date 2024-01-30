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
    List<CartEntity> findByUserId(Long user_id);

    // Find specific item in the cart based on user Id and product Id
    Optional<CartEntity> findByUserIdAndProductId(Long user_id, Long product_id);

    // Find all carts for a specific user
    @Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
    List<CartEntity> findAllByIdUser(Long user_id);
    
    /// Calculate the cost of a specific cart
    @Query(value = "SELECT c.amount * c.product.price FROM cart c WHERE c.id = ?1", nativeQuery = true)
    Double calculateCartCost(Long id);

    // Calculate the total cost of all carts for a specific user
    @Query(value = "SELECT SUM(c.amount * c.product.price) FROM cart c WHERE c.user_id = ?1", nativeQuery = true)
    Double calculateTotalCartCost(Long user_id);

    // Remove all carts for a specific user
    @Modifying
    @Query(value = "DELETE FROM cart WHERE user_id = ?1", nativeQuery = true)
    void deleteByUserId(Long user_id);

    // Method to reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
