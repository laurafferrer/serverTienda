package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.ProductEntity;

public interface CategoryRepository extends JpaRepository<CategoryRepository, Long> {

    Optional<CategoryRepository> findByName(String name);

    Optional<CategoryRepository> findById(Long id);

    @Query(value = "SELECT c.*, count p(p.id) FROM category c, product p WHERE c.id = p.idCategory GROUP BY c.id ORDER BY count(c.id) DESC", nativeQuery = true)
    Page <ProductEntity> findCategoryByProductDesc(Pageable pageable);

    // Method to reset the auto-increment counter for the user table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
