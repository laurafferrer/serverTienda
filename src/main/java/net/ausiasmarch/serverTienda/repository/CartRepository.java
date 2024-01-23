package net.ausiasmarch.serverTienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get page of cart for a specific user
    List<CartEntity> findByIdUser(Long user_id);

    // Find specific item in the cart base on user Id and product Id
    Optional<CartEntity> findByIdUserAndIdProduct(Long user_id, Long product_id);

    // Remove all cart for a specific user
    @Query(value = "DELETE FROM cart WHERE user_id = ?1", nativeQuery = true)
    void deleteByIdUser(Long user_id);

    // Find all carts for a specific user
    @Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
    List<CartEntity> findAllByIdUser(Long user_id);

    // Method to reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
