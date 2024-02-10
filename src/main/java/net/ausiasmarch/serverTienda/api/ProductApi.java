/*
   API controller for managing products.
   Provides endpoints for retrieving, creating, updating, and deleting products.
*/
package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    ProductService oProductService;

    /*
     * Ge product by ID.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.get(id));
    }

/*
     * Get all products.
     * 
     * @param oPageable Pageable object.
     * @param strFilter Filter string.
     * @return ResponseEntity with Page<ProductEntity>.
     */
    @GetMapping("")
    public ResponseEntity<Page<ProductEntity>> getPage(
        Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter,
        @RequestParam(name = "category", required = false) Long category_id) {
        return new ResponseEntity<>(oProductService.getPage(oPageable, category_id, strFilter), HttpStatus.OK);
    }

    /*
     * Create new product.
     * 
     * @param oProductEntity ProductEntity object.
     * @return ResponseEntity with ProductEntity.
     */
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.create(oProductEntity));
    }

    /*
     * Update existing product.
     * 
     * @param oProductEntity ProductEntity object.
     * @return ResponseEntity with ProductEntity.
     */
    @PutMapping("")
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.update(oProductEntity));
    }

    /*
     * Delete existing product.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.delete(id));
    }

    /*
     * Delete all products.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oProductService.empty());
    }

    /*
     * Generate random products up to a given amount.
     * 
     * @param amount Number of products to be generated.
     * @return ResponseEntity with the number of generated products.
     */
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Long amount) {
        return ResponseEntity.ok(oProductService.populate(amount));
    }

    /*
     * Get random product.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/randomProduct")
    public ResponseEntity<ProductEntity> getOneRandom() {
        return ResponseEntity.ok(oProductService.getOneRandom());
    }

    /*
     * Get products by category.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byCategory/{id}")
    public ResponseEntity<Page<ProductEntity>> getByCategoryId(@PathVariable("id") Long id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByCategoryId(id, oPageable));
    }

    /*
     * Get products by stock ascending.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byStockAsc")
    public ResponseEntity<Page<ProductEntity>> getByStockAsc(@PathVariable("stock") String stock, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByStockAsc(oPageable));
    }

    /*
     * Get products by stock descending.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<ProductEntity>> getByPriceAsc(@PathVariable("price") String price, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceAsc(oPageable));
    }

    /*
     * Get products by price descending.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<ProductEntity>> getByPriceDesc(@PathVariable("price") String price, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDesc(oPageable));
    }

    /*
     * Get products by price ascending and category.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byPriceAscAndCategoryId/{id}")
    public ResponseEntity<Page<ProductEntity>> getByPriceAscAndCategoryId(@PathVariable("price") String price, @PathVariable("category_id") Long category_id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceAscAndCategoryId(category_id, oPageable));

    }

    /*
     * Get products by price descending and category.
     * 
     * @param id Product's ID.
     * @return ResponseEntity with ProductEntity.
     */
    @GetMapping("/byPriceDescAndCategoryId/{id}")
    public ResponseEntity<Page<ProductEntity>> getByPriceDescAndCategoryId(@PathVariable("price") String price, @PathVariable("category_id") Long category_id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDescAndCategoryId(category_id, oPageable));
    }

}