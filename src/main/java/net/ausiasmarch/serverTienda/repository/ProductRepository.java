package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Find product by Id Category
    Page <ProductEntity> findBycategory_id(Long category_id, Pageable pageable);

    // Find product by name
    @Query(value = "SELECT * FROM product WHERE name LIKE %?1%", nativeQuery = true)
    Page <ProductEntity> findByName(String name, Pageable oPageable);

    // Find product by stock ascending
    @Query(value = "SELECT * FROM product WHERE stock <= ?1 ORDER BY stock ASC", nativeQuery = true)
    Page <ProductEntity> findByStockAsc(Pageable pageable);

    // Find products by price ascending
    @Query(value = "SELECT * FROM product ORDER BY price ASC", nativeQuery = true)
    Page <ProductEntity> findByPriceAsc(Pageable pageable);

    // Find products by price descending
    @Query(value = "SELECT * FROM product ORDER BY price DESC", nativeQuery = true)
    Page <ProductEntity> findByPriceDesc(Pageable pageable);

    // Find products by price ascending and category
    @Query(value = "SELECT * FROM product WHERE category_id = ?1 ORDER BY price ASC", nativeQuery = true)
    Page <ProductEntity> findByPriceAscAndcategory_id(Long id, Pageable pageable);

    // Find products by price descending and category
    @Query(value = "SELECT * FROM product WHERE category_id = ?1 ORDER BY price DESC", nativeQuery = true)
    Page <ProductEntity> findByPriceDescAndcategory_id(Long id, Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
