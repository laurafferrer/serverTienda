package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findById(Long id);

    //findCartByUser
    @Query(value = "SELECT u.*, count c(c.id) FROM cart c, user u WHERE c.id = u.idUser GROUP BY c.id ORDER BY count(c.id) DESC", nativeQuery = true)
    Page<CartEntity> findCartByUserDesc(Pageable pageable);

    //findCartByProduct
    @Query(value = "SELECT p.*, count c(c.id) FROM cart c, product p WHERE c.id = p.idProduct GROUP BY c.id ORDER BY count(c.id) DESC", nativeQuery = true)
    Page<CartEntity> findCartByProductDesc(Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
