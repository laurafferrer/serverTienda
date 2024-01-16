package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.serverTienda.entity.CaptchaEntity;

public interface CaptchaRepository extends JpaRepository<CaptchaEntity, Long> {
    
}
