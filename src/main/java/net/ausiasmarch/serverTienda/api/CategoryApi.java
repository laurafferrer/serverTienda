package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.service.CategoryService;
import net.ausiasmarch.serverTienda.entity.CategoryEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryApi {

    @Autowired
    CategoryService oCategoryService;

    // Get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.get(id));
    }

    // Get all categories
    @GetMapping("")
    public ResponseEntity<Page<CategoryEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.getPage(oPageable));
    }

    // Create new category
    @PostMapping("")
    public ResponseEntity<Long> create(CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.create(oCategoryEntity));
    }

    // Update existing category
    @PutMapping("")
    public ResponseEntity<CategoryEntity> update(CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.update(oCategoryEntity));
    }

    // Delete existing category
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.delete(id));
    }

    // Remove all categories
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCategoryService.empty());
    }

    // Get categories by the quantity products associated in ascending order
    @GetMapping("/quantityProductsAsc")
    public ResponseEntity<Page<CategoryEntity>> findByQuantityProductsAsc(Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.findByQuantityProductsAsc(oPageable));
    }   
    
}
