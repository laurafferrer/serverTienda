/*
   CategoryRepository interface for managing CategoryEntity persistence.

   JpaRepository provides standard CRUD operations and queries for the CategoryEntity entity.

   Additional custom queries for specific operations on the CategoryEntity table.
*/
package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Method to get categories by the quantity of associated products in ascending order
    @Query(value = "SELECT * FROM category ORDER BY (SELECT COUNT(*) FROM product WHERE product.category_id = category.id) ASC", nativeQuery = true)
    Page<CategoryEntity> findByQuantityProductsAsc(Pageable oPageable);

    // Method to reset the auto-increment value of the primary key in the category table
    @Modifying
    @Query(value = "ALTER TABLE category AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
