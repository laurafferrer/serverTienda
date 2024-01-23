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

    // Find users ordered by te count of purchase detail in descending order
    @Query(value = "SELECT u.*, count(pd.id) FROM user u, purchaseDetail pd WHERE u.id = pd.user_id GROUP BY u.id ORDER BY count(u.id) DESC", nativeQuery = true)
    Page<UserEntity> findUsersByPurchaseDetailDesc(Pageable pageable);

    // Find users ordered by the count of purchase detail in ascending order
    @Query(value = "SELECT u.*, count(pd.id) FROM user u, purchaseDetail pd WHERE u.id = pd.user_id GROUP BY u.id ORDER BY count(u.id) ASC", nativeQuery = true)
    Page<UserEntity> findUsersByPurchaseDetailAsc(Pageable pageable);

    // Reset the auto-increment of the table user
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    
}
