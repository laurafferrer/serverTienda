package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.OrderingEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional <ProductEntity> findById(Long id);

    @Query(value = "SELECT * FROM product WHERE idCategory = ?1", nativeQuery = true)
    Page <ProductEntity> findByIdCategory(Long id);

    @Query(value = "SELECT * FROM product WHERE name LIKE %?1%", nativeQuery = true)
    Page <ProductEntity> findByName(String name);

    @Query(value = "SELECT d.*, count p(p.id) FROM purchaseDetail d, product p WHERE p.id = d.idProduct GROUP BY p.id ORDER BY count(p.id) DESC, nativeQuery = true")
    Page<OrderingEntity> findProductByPurchaseDetailDesc(Pageable pageable);

    // LUEGO SE USARÁ PARA QUE CUANTO EL STOCK BAJE DE CIERTO NÚMERO SE HAGA UN PEDIDO AL PROVEEDOR
    @Query(value = "SELECT * FROM product WHERE stock <= ?1", nativeQuery = true)
    Page <ProductEntity> findByStock(int stock);

    @Modifying
    @Query(value = "ALTER TABLE product AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
