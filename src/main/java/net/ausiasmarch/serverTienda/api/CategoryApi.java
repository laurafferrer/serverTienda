/*
   Controller for managing categories.
   Provides endpoints for retrieving, creating, updating, 
   and deleting categories.
*/
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.ausiasmarch.serverTienda.service.CategoryService;
import net.ausiasmarch.serverTienda.entity.CategoryEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryApi {

    @Autowired
    CategoryService oCategoryService;

    /*
     * Endpoint for retrieving a category by id.
     * 
     * @param id Long containing the id of the category to be retrieved.
     * @return ResponseEntity containing the category.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.get(id));
    }

    /*
     * Endpoint for retrieving all categories.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity containing the categories.
     */
    @GetMapping("")
    public ResponseEntity<Page<CategoryEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.getPage(oPageable));
    }

    /*
     * Endpoint for creating a new category.
     * 
     * @param oCategoryEntity CategoryEntity object containing the category to be created.
     * @return ResponseEntity containing the id of the created category.
     */
    @PostMapping("")
    public ResponseEntity<Long> create(@Valid @RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.create(oCategoryEntity));
    }

    /*
     * Endpoint for updating an existing category.
     * 
     * @param oCategoryEntity CategoryEntity object containing the category to be updated.
     * @return ResponseEntity containing the updated category.
     */
    @PutMapping("")
    public ResponseEntity<CategoryEntity> update(@RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.update(oCategoryEntity));
    }

    /*
     * Endpoint for deleting an existing category.
     * 
     * @param id Long containing the id of the category to be deleted.
     * @return ResponseEntity containing the id of the deleted category.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.delete(id));
    }

    /*
     * Endpoint for deleting all existing categories.
     * 
     * @return ResponseEntity containing the number of deleted categories.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCategoryService.empty());
    }

    /*
     * Endpoint for populating the categories table with random data.
     * 
     * @param amount Long containing the number of categories to be generated.
     * @return ResponseEntity containing the number of generated categories.
     */
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Long amount) {
        return ResponseEntity.ok(oCategoryService.populate(amount));
    }

    /*
     * Endpoint for retrieving all categories by the quantity products associated in ascending purchase.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity containing the categories.
     */
    @GetMapping("/quantityProductsAsc")
    public ResponseEntity<Page<CategoryEntity>> findByQuantityProductsAsc(Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.findByQuantityProductsAsc(oPageable));
    }   
    
}
