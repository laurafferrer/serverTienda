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

import net.ausiasmarch.serverTienda.service.CartService;
import net.ausiasmarch.serverTienda.entity.CartEntity;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    CartService oCartService;

    // Get cart by id
    @GetMapping("/{id}")
    public ResponseEntity<CartEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.get(id));
    }

    // Get all carts
    @GetMapping("")
    public ResponseEntity<Page<CartEntity>> getPage(
        Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oCartService.getPage(oPageable));
    }

    // Get all carts for a specific user
    @GetMapping("/byUser/{idUser}")
    public ResponseEntity<Page<CartEntity>> getPageByUser(
        @PathVariable("idUser") Long idUser,
        Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oCartService.getPageByUser(idUser, oPageable));
    }

    // Get specific item in the cart base on user Id and product Id
    @GetMapping("/byUserAndIdProduct/{idUser}/{idProduct}")
    public ResponseEntity<CartEntity> getByUserAndIdProduct(
        @PathVariable("idUser") Long idUser,
        @PathVariable("idProduct") Long idProduct) {
        return ResponseEntity.ok(oCartService.findByUserAndIdProduct(idUser, idProduct));
    }

    // Create new cart
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.create(oCartEntity));
    }

    // Update existing cart
    @PutMapping("")
    public ResponseEntity<CartEntity> update(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.update(oCartEntity));
    }

    // Delete existing cart
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.delete(id));
    }

    // Remove all carts
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCartService.emptyTable());
    }
    
    // Delete all carts for a specific user
    @DeleteMapping("/byUser/{idUser}")
    public ResponseEntity<?> deleteByIdUser(@PathVariable("idUser") Long idUser) {
        oCartService.deleteByIdUser(idUser);
        return ResponseEntity.ok().build();
    }
    
}
