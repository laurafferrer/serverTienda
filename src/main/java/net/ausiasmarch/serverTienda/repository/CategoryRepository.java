package net.ausiasmarch.serverTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryRepository, Long> {

    Optional<CategoryRepository> findByName(String name);

    Optional<CategoryRepository> findById(Long id);
    
}
