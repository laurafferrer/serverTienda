/*
   CaptchaRepository interface for managing CaptchaEntity persistence.
   
   JpaRepository provides standard CRUD operations and queries for the CaptchaEntity entity.
*/
package net.ausiasmarch.serverTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.serverTienda.entity.CaptchaEntity;

public interface CaptchaRepository extends JpaRepository<CaptchaEntity, Long> {
    // No additional methods needed as JpaRepository provides standard functionality.
}
