package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.CategoryEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository oCategoryRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get category by ID
    public CategoryEntity get(Long id) {
        return oCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    //Get page of categories
    public Page<CategoryEntity> getPage(Pageable oPageable) {
        return oCategoryRepository.findAll(oPageable);
    }

    //Create new category
    public Long create(CategoryEntity oCategoryEntity) {
        oSessionService.onlyAdmins();
        oCategoryEntity.setId(null);
        return oCategoryRepository.save(oCategoryEntity).getId();
    }

    // Update an existing category
    public CategoryEntity update(CategoryEntity oCategoryEntity) {
        oSessionService.onlyAdmins();
        return oCategoryRepository.save(oCategoryEntity);
    }

    // Delete an existing category by ID
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oCategoryRepository.deleteById(id);
        return id;
    }

    // Get random category
    public CategoryEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oCategoryRepository.count()), 1);
        return oCategoryRepository.findAll(oPageable).getContent().get(0);
    }

    // Get categories by quantity products
    public Page<CategoryEntity> findByQuantityProductsAsc(Pageable oPageable) {
        return oCategoryRepository.findByQuantityProductsAsc(oPageable);
    }

    // Empty the category table
    public Long emptyTable() {
        oCategoryRepository.deleteAll();
        oCategoryRepository.resetAutoIncrement();
        oCategoryRepository.flush();
        return oCategoryRepository.count();
    }
    
}
