package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
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
import net.ausiasmarch.serverTienda.entity.UserEntity;


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

    // Get cart by user id
    @GetMapping("/byUser/{user_id}")
    public ResponseEntity<List<CartEntity>> getCartByUser(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.getCartByUser(user_id));
    }

    // Get all carts
    @GetMapping("")
    public ResponseEntity<Page<CartEntity>> getPage(
        Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oCartService.getPage(oPageable));
    }

    // Get cart by user id and product id
    @GetMapping("/byUserAndproduct_id/{user_id}/{product_id}")
    public ResponseEntity<CartEntity> getByUserAndProdcut(@PathVariable("user_id") Long user_id, @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oCartService.getCartByUserAndProduct(user_id, product_id));
    }

    // Get page of carts
    @GetMapping("")
    public ResponseEntity<Page<CartEntity>> getPage( @PageableDefault(size = 40, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oCartService.getPage(oPageable));
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

    // Delete existing cart by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.delete(id));
    }

    // Delete all carts
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCartService.emptyTable());
    }
    
    // Delete all carts for a specific user
    @DeleteMapping("/byUser/{user_id}")
    public ResponseEntity<?> deleteByUser(@PathVariable("user_id") UserEntity user) {
        oCartService.deleteByuser_id(user);
        return ResponseEntity.ok().build();
    }
    
}
