package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.serverTienda.entity.PendentEntity;

public interface PendentRepository extends JpaRepository<PendentEntity, Long>{

    Optional<PendentEntity> findByToken(String token);
    
}
