package net.ausiasmarch.serverTienda.api;

import java.util.Optional;

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

    // Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.get(id));
    }

    // Get all products
    @GetMapping("")
    public ResponseEntity<Page<ProductEntity>> getPage(
        Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oProductService.getPage(oPageable));
    }

    // Create new product
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.create(oProductEntity));
    }

    // Update existing product
    @PutMapping("")
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.update(oProductEntity));
    }

    // Delete existing product
    @PutMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.delete(id));
    }

    // Remove all products
    @DeleteMapping("/emptyTable")
    public ResponseEntity<Long> emptyTable() {
        return ResponseEntity.ok(oProductService.emptyTable());
    }

    // Get random product
    @GetMapping("/randomProduct")
    public ResponseEntity<ProductEntity> getOneRandom() {
        return ResponseEntity.ok(oProductService.getOneRandom());
    }

    // Get products by category
    @GetMapping("/byCategory/{id}")
    public ResponseEntity<Optional<ProductEntity>> getByCategory(
        Pageable oPageable,
        @PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.getByCategory(id, oPageable));
    }

    // Get products by stock ascending
    @GetMapping("/byStockAsc")
    public ResponseEntity<Page<ProductEntity>> getByStockAsc(Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByStockAsc(oPageable));
    }

    // Get products by price ascending
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<ProductEntity>> getByPriceAsc(Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceAsc(oPageable));
    }

    // Get products by price descending
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<ProductEntity>> getByPriceDesc(Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDesc(oPageable));
    }

    // Get products by price ascending and category
    @GetMapping("/byPriceAscAndIdCategory/{id}")
    public ResponseEntity<Page<ProductEntity>> getByPriceAscAndIdCategory(
        Pageable oPageable,
        @PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.getByPriceAscAndIdCategory(id, oPageable));
    }

    // Get products by price descending and category
    @GetMapping("/byPriceDescAndIdCategory/{id}")
    public ResponseEntity<Page<ProductEntity>> getByPriceDescAndIdCategory(
        Pageable oPageable,
        @PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.getByPriceDescAndIdCategory(id, oPageable));
    }

}



    
    

