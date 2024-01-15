package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    // USO AL IMPLEMENTAR CAMBIO DE CONTRASEÑA POR EMAIL. NO BORRAR
    //Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT u.*, count o(o.id) FROM user u, ordering o WHERE u.id = o.idUser GROUP BY u.id ORDER BY count(u.id) DESC, nativeQuery = true")
    Page<UserEntity> findUsersByOrderingDesc(Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE length(?1) >= 3 AND (username LIKE %?1% OR name LIKE %?1% OR lastName1 LIKE %?1% OR lastName2 LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    Page<UserEntity> findByUserByNameOrLastnameContainingIgnoreClase(String searchText, String filter, String filter2, String filter3, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
