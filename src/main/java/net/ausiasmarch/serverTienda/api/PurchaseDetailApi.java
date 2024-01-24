/*
   API controller for managing purchase details.
   Provides endpoints for retrieving, creating, updating, and deleting purchase details.
*/
package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.serverTienda.service.PurchaseDetailService;
import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchaseDetail")
public class PurchaseDetailApi {

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    /*
     * Endpoint to get purchase detail by its ID.
     *
     * @param id ID of the purchase detail.
     * @return ResponseEntity with the requested PurchaseDetailEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDetailEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.get(id));
    }

    /*
     * Endpoint to get all purchase details with optional filtering.
     *
     * @param oPageable Pageable object for pagination.
     * @param strFilter Optional filter parameter.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("")
    public ResponseEntity<Page<PurchaseDetailEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oPurchaseDetailService.getPage(oPageable));
    }

    /*
     * Endpoint to create a new purchase detail.
     *
     * @param oPurchaseDetailEntity PurchaseDetailEntity object to be created.
     * @return ResponseEntity with the ID of the created purchase detail.
     */
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.create(oPurchaseDetailEntity));
    }

    /*
     * Endpoint to update an existing purchase detail.
     *
     * @param oPurchaseDetailEntity PurchaseDetailEntity object with updated information.
     * @return ResponseEntity with the updated PurchaseDetailEntity.
     */
    @PutMapping("")
    public ResponseEntity<PurchaseDetailEntity> update(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.update(oPurchaseDetailEntity));
    }

    /*
     * Endpoint to delete an existing purchase detail.
     *
     * @param id ID of the purchase detail to be deleted.
     * @return ResponseEntity with the deleted purchase detail ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.delete(id));
    }

    /*
     * Endpoint to get purchase details by order ID.
     *
     * @param oPageable Pageable object for pagination.
     * @param order_id ID of the order to filter by.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("/byOrderId/{order_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByOrderId(
            Pageable oPageable,
            @PathVariable("order_id") Long order_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByOrderId(order_id, oPageable));
    }

    /*
     * Endpoint to get purchase details by product ID.
     *
     * @param oPageable Pageable object for pagination.
     * @param product_id ID of the product to filter by.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("/byProductId/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByProductId(
            Pageable oPageable,
            @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByProductId(product_id, oPageable));
    }

    /*
     * Endpoint to get purchase details by both order ID and product ID.
     *
     * @param oPageable Pageable object for pagination.
     * @param order_id ID of the order to filter by.
     * @param product_id ID of the product to filter by.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("/byOrderIdAndProductId/{order_id}/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByOrderIdAndProductId(
            Pageable oPageable,
            @PathVariable("order_id") Long order_id,
            @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oPurchaseDetailService.findByOrderIdAndProductId(order_id, product_id, oPageable));
    }

    /*
     * Endpoint to get all purchase details sorted by price in descending order.
     *
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceDesc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceDesc(oPageable));
    }

    /*
     * Endpoint to get all purchase details sorted by price in ascending order.
     *
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity with a Page of PurchaseDetailEntity.
     */
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceAsc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceAsc(oPageable));
    }

    /*
     * Endpoint to empty the purchase detail table.
     *
     * @return ResponseEntity with the count of deleted records.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPurchaseDetailService.empty());
    }
}
