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
@RequestMapping("/order")
public class PurchaseApi {

    @Autowired
    PurchaseService oOrderService;

    /*
     * Get order by ID.
     * 
     * @param id Order's ID.
     * @return ResponseEntity with OrderEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderService.get(id));
    }

    /*
     * Get all orders.
     * 
     * @param oPageable Pageable object.
     * @param strFilter Filter string.
     * @return ResponseEntity with Page<OrderEntity>.
     */
    @GetMapping("")
    public ResponseEntity<Page<PurchaseEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oOrderService.getPage(oPageable));
    }

    /*
     * Get order by user_id.
     * 
     * @param oPageable Pageable object.
     * @param user_id User's ID.
     * @return ResponseEntity with Page<OrderEntity>.
     */
    @GetMapping("/byUserId/{user_id}")
    public ResponseEntity<Page<PurchaseEntity>> findByUserId(
            Pageable oPageable,
            @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oOrderService.findByUserId(user_id, oPageable));
    }

    /*
     * Create new order.
     * 
     * @param oOrderEntity OrderEntity object.
     * @return ResponseEntity with OrderEntity.
     */
    @PostMapping("")
    public ResponseEntity<PurchaseEntity> create(@RequestBody PurchaseEntity oOrderEntity) {
        return ResponseEntity.ok(oOrderService.create(oOrderEntity));
    }

    /*
     * Update existing order.
     * 
     * @param oOrderEntity OrderEntity object.
     * @return ResponseEntity with OrderEntity.
     */
    @PutMapping("")
    public ResponseEntity<PurchaseEntity> update(@RequestBody PurchaseEntity oOrderEntity) {
        return ResponseEntity.ok(oOrderService.update(oOrderEntity));
    }

    /*
     * Delete existing order.
     * 
     * @param id Order's ID.
     * @return ResponseEntity with OrderEntity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderService.delete(id));
    }

    /*
     * Delete all orders.
     * 
     * @return ResponseEntity with OrderEntity.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oOrderService.empty());
    }

    /*
     * Get orders by date order desc.
     * 
     * @param oPageable Pageable object.
     * @return ResponseEntity with Page<OrderEntity>.
     */
    @GetMapping("/byDateOrderDesc")
    public ResponseEntity<Page<PurchaseEntity>> findOrderByDateOrderDesc(Pageable pageable) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderDesc(pageable));
    }

    /*
     * Get orders by date order asc.
     * 
     * @param oPageable Pageable object.
     * @return ResponseEntity with Page<OrderEntity>.
     */
    @GetMapping("/byDateOrderAsc")
    public ResponseEntity<Page<PurchaseEntity>> findOrderByDateOrderAsc(Pageable pageable) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderAsc(pageable));
    }

    /*
     * Get orders by date order containing.
     * 
     * @param oPageable Pageable object.
     * @param date_order Date order.
     * @return ResponseEntity with Page<OrderEntity>.
     */
    @GetMapping("/byDateOrderContaining/{date_order}")
    public ResponseEntity<Page<PurchaseEntity>> findOrderByDateOrderContaining(
            Pageable pageable,
            @PathVariable("date_order") String date_order) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderContaining(date_order, pageable));
    }     
    
}
