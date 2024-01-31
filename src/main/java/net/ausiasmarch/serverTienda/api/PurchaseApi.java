/*
   API controller for managing purchases.
   Provides endpoints for retrieving, creating, updating, and deleting purchases.
*/
package net.ausiasmarch.serverTienda.api;

import java.util.List;

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

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.entity.PurchaseEntity;
import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.service.CartService;
import net.ausiasmarch.serverTienda.service.PurchaseService;
import net.ausiasmarch.serverTienda.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchase")
public class PurchaseApi {

    @Autowired
    PurchaseService oPurchaseService;

    @Autowired
    UserService oUserService;

    @Autowired
    CartService oCartService;

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
     * Get random purchase.
     * 
     * @return ResponseEntity with purchaseEntity.
     */
    @GetMapping("/random")
    public ResponseEntity<PurchaseEntity> getRandom() {
        PurchaseEntity oPurchaseEntity = oPurchaseService.getOneRandom();
        return ResponseEntity.ok(oPurchaseEntity);
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
     * Purchase one cart.
     * 
     * @param idUser User's ID.
     * @param idCart Cart's ID.
     * @return ResponseEntity with purchaseEntity.
     */
    @PostMapping("/MakePurchaseSingleCart/{idUser}/{id}")
    public ResponseEntity<PurchaseEntity> makePurchaseSingleCart(@PathVariable Long idUser, @PathVariable Long id) {
        UserEntity oUserEntity = oUserService.get(idUser);
        CartEntity oCartEntity = oCartService.get(id);

        PurchaseEntity oPurchaseEntity = oPurchaseService.makePurchaseSingleCart(oCartEntity, oUserEntity);

        return new ResponseEntity<>(oPurchaseEntity, HttpStatus.CREATED);
    }

    /*
     * Purchase all carts.
     * 
     * @param idUser User's ID.
     * @return ResponseEntity with purchaseEntity.
     */
    @PostMapping("/MakePurchaseAllCarts/{idUser}")
    public ResponseEntity<PurchaseEntity> makePurchaseAllCarts(@PathVariable Long idUser) {
        UserEntity oUserEntity = oUserService.get(idUser);
        List<CartEntity> oCartsEntity = oCartService.getByUser(idUser);

        PurchaseEntity oPurchaseEntity = oPurchaseService.makePurchaseAllCarts(oCartsEntity, oUserEntity);

        return new ResponseEntity<>(oPurchaseEntity, HttpStatus.CREATED);
    }

    /*
     * Populate database with random purchases.
     * 
     * @param amount Number of purchases to create.
     * @return ResponseEntity with purchaseEntity.
     */
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") int amount) {
        return ResponseEntity.ok(oPurchaseService.populate(amount));
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
