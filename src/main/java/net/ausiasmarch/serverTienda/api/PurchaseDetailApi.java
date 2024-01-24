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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.service.PurchaseDetailService;
import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchaseDetail")
public class PurchaseDetailApi {

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    // Get purchase detail by id
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDetailEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.get(id));
    }

    // Get all purchase details
    @GetMapping("")
    public ResponseEntity<Page<PurchaseDetailEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oPurchaseDetailService.getPage(oPageable));
    }

    // Create new purchase detail
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.create(oPurchaseDetailEntity));
    }

    // Update existing purchase detail
    @PutMapping("")
    public ResponseEntity<PurchaseDetailEntity> update(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.update(oPurchaseDetailEntity));
    }

    // Delete existing purchase detail
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.delete(id));
    }

    // Get purchase detail by order_id
    @GetMapping("/byOrderId/{order_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByOrderId(
            Pageable oPageable,
            @PathVariable("order_id") Long order_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByOrderId(order_id, oPageable));
    }

    // Get purchase detail by product_id
    @GetMapping("/byProductId/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByProductId(
            Pageable oPageable,
            @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByProductId(product_id, oPageable));
    }

    // Get purchase detail by order_id and product_id
    @GetMapping("/byOrderIdAndProductId/{order_id}/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByOrderIdAndProductId(
            Pageable oPageable,
            @PathVariable("order_id") Long order_id,
            @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByOrderIdAndProductId(order_id, product_id, oPageable));
    }

    // Get purchase dewtail by price desc
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceDesc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceDesc(oPageable));
    }

    // Get purchase detail by price asc
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceAsc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceAsc(oPageable));
    }

    // Empty the purchase detail table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPurchaseDetailService.empty());
    }
    
}
