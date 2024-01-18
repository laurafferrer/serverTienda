package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Methos to get categories by the quantity products associated in ascending order
    @Query(value = "SELECT * FROM category ORDER BY (SELECT COUNT(*) FROM product WHERE product.idCategory = category.id) ASC", nativeQuery = true)
    Page<CategoryEntity> findByQuantityProductsAsc(Pageable oPageable);

    // Method to reset the auto-increment value primary key in the category table
    @Modifying
    @Query(value = "ALTER TABLE category AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
