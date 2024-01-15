package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page <ProductEntity> findByIdCategory(Long id);

    Page <ProductEntity> findBySize(String size);

    @Query(value = "SELECT * FROM product WHERE name LIKE %?1%", nativeQuery = true)
    Page <ProductEntity> findByName(String name);

    @Query(value = "SELECT p.*, count c(c.id) FROM product p, category c WHERE p.id = c.idCategory GROUP BY p.id ORDER BY count(p.id) DESC", nativeQuery = true)
    Page <ProductEntity> findProductByCategoryDesc();

    // LUEGO SE USARÁ PARA QUE CUANTO EL STOCK BAJE DE CIERTO NÚMERO SE HAGA UN PEDIDO AL PROVEEDOR
    @Query(value = "SELECT * FROM product WHERE stock <= ?1", nativeQuery = true)
    Page <ProductEntity> findByStock(int stock);

    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
