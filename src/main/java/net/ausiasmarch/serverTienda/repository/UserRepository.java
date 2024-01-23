package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.serverTienda.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Find User by Username
    Optional<UserEntity> findByUsername(String username);

    // Find User by Username and Password
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    // USO AL IMPLEMENTAR CAMBIO DE CONTRASEÑA POR EMAIL. NO BORRAR
    //Optional<UserEntity> findByEmail(String email);

    // Find users ordered by count of orders in descending order
    @Query(value = "SELECT u.*, count o(o.id) FROM user u, ordering o WHERE u.id = o.user_id GROUP BY u.id ORDER BY count(u.id) DESC, nativeQuery = true")
    Page<UserEntity> findUsersByOrderingDesc(Pageable pageable);

    // Find users by name or lastname or username or email
    @Query(value = "SELECT * FROM user WHERE length(?1) >= 3 AND (username LIKE %?1% OR name LIKE %?1% OR surname LIKE %?1% OR last_name LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    Page<UserEntity> findByUserByNameOrLastnameContainingIgnoreClase(String searchText, String filter, String filter2, String filter3, Pageable pageable);

    // Reset the auto-increment of the table user
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
