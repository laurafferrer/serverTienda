/*
   PendentRepository interface for managing PendentEntity persistence.
   JpaRepository provides standard CRUD operations and queries for the PendentEntity entity.
   Additional custom query to find a PendentEntity by its token.
*/

package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.serverTienda.entity.PendentEntity;

public interface PendentRepository extends JpaRepository<PendentEntity, Long>{

    // Find a PendentEntity by its unique token
    Optional<PendentEntity> findByToken(String token);
    
}
