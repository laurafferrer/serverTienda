/*
   API controller for managing purchases.
   Provides endpoints for retrieving, creating, updating, and deleting purchases.
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.entity.PurchaseEntity;
import net.ausiasmarch.serverTienda.service.PurchaseService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchase")
public class PurchaseApi {

    @Autowired
    PurchaseService oPurchaseService;

    /*
     * Get purchase by ID.
     * 
     * @param id purchase's ID.
     * @return ResponseEntity with purchaseEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseService.get(id));
    }

    /*
     * Get all purchases.
     * 
     * @param oPageable Pageable object.
     * @param strFilter Filter string.
     * @return ResponseEntity with Page<purchaseEntity>.
     */
    @GetMapping("")
    public ResponseEntity<Page<PurchaseEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oPurchaseService.getPage(oPageable));
    }

    /*
     * Get purchase by user_id.
     * 
     * @param oPageable Pageable object.
     * @param user_id User's ID.
     * @return ResponseEntity with Page<purchaseEntity>.
     */
    @GetMapping("/byUserId/{user_id}")
    public ResponseEntity<Page<PurchaseEntity>> findByUserId(
            Pageable oPageable,
            @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oPurchaseService.findByUserId(user_id, oPageable));
    }

    /*
     * Create new purchase.
     * 
     * @param oPurchaseEntity purchaseEntity object.
     * @return ResponseEntity with purchaseEntity.
     */
    @PostMapping("")
    public ResponseEntity<PurchaseEntity> create(@RequestBody PurchaseEntity oPurchaseEntity) {
        return ResponseEntity.ok(oPurchaseService.create(oPurchaseEntity));
    }

    /*
     * Update existing purchase.
     * 
     * @param opurchaseEntity purchaseEntity object.
     * @return ResponseEntity with purchaseEntity.
     */
    @PutMapping("")
    public ResponseEntity<PurchaseEntity> update(@RequestBody PurchaseEntity oPurchaseEntity) {
        return ResponseEntity.ok(oPurchaseService.update(oPurchaseEntity));
    }

    /*
     * Delete existing purchase.
     * 
     * @param id purchase's ID.
     * @return ResponseEntity with purchaseEntity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseService.delete(id));
    }

    /*
     * Delete all purchases.
     * 
     * @return ResponseEntity with purchaseEntity.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPurchaseService.empty());
    }

    /*
     * Get purchases by date purchase desc.
     * 
     * @param oPageable Pageable object.
     * @return ResponseEntity with Page<purchaseEntity>.
     */
    @GetMapping("/byDatePurchaseDesc")
    public ResponseEntity<Page<PurchaseEntity>> findPurchaseByDateOrderDesc(Pageable pageable) {
        return ResponseEntity.ok(oPurchaseService.findPurchaseByDateOrderDesc(pageable));
    }

    /*
     * Get purchases by date order asc.
     * 
     * @param oPageable Pageable object.
     * @return ResponseEntity with Page<purchaseEntity>.
     */
    @GetMapping("/byDatePurchaseAsc")
    public ResponseEntity<Page<PurchaseEntity>> findPurchaseByDateOrderAsc(Pageable pageable) {
        return ResponseEntity.ok(oPurchaseService.findPurchaseByDateOrderAsc(pageable));
    }

    /*
     * Get purchases by date purchase containing.
     * 
     * @param oPageable Pageable object.
     * @param date_purchase Date purchase.
     * @return ResponseEntity with Page<purchaseEntity>.
     */
    @GetMapping("/byDatePurchaseContaining/{date_purchase}")
    public ResponseEntity<Page<PurchaseEntity>> findPurchaseByDateOrderContaining(
            Pageable pageable,
            @PathVariable("date_purchase") String date_purchase) {
        return ResponseEntity.ok(oPurchaseService.findPurchaseByDateOrderContaining(date_purchase, pageable));
    }     
    
}
