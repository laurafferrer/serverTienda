/*
   Controller for managing categories.
   Provides endpoints for retrieving, creating, updating, and deleting categories.
*/
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
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.service.CartService;
import net.ausiasmarch.serverTienda.entity.CartEntity;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    CartService oCartService;

    /*
     * Endpoint for retrieving a cart by id.
     * 
     * @param id Long containing the id of the cart to be retrieved.
     * @return ResponseEntity containing the cart.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CartEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.get(id));
    }

    /*
     * Endpoint for retrieving a cart by user id.
     * 
     * @param user_id Long containing the id of the user to be retrieved.
     * @return ResponseEntity containing the cart.
     */
    @GetMapping("/byUser/{user_id}")
    public ResponseEntity<Page<CartEntity>> getCartByUser(@PathVariable("user_id") Long user_id, @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oCartService.getCartByUser(user_id, oPageable));
    }

    /*
     * Endpoint for retrieving a cart by user id and product id.
     * 
     * @param user_id Long containing the id of the user to be retrieved.
     * @param product_id Long containing the id of the product to be retrieved.
     * @return ResponseEntity containing the cart.
     */
    @GetMapping("/byUserAndProductId/{user_id}/{product_id}")
    public ResponseEntity<CartEntity> getByUserAndProdcut(@PathVariable("user_id") Long user_id, @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oCartService.getByUserAndProduct(user_id, product_id));
    }

    /*
     * Endpoint for retrieving all carts.
     * 
     * @param oPageable Pageable object for pagination.
     * @return ResponseEntity containing the carts.
     */
    @GetMapping("")
    public ResponseEntity<Page<CartEntity>> getPage( @PageableDefault(size = 40, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oCartService.getPage(oPageable));
    }

    /*
     * Endpoint for retrieving all carts for a specific user.
     * 
     * @param user_id Long containing the id of the user to be retrieved.
     * @return ResponseEntity containing the carts.
     */
    @GetMapping("/allByUser/{user_id}")
    public ResponseEntity<List<CartEntity>> getAllByIdUser(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.getAllByIdUser(user_id));
    }

    /*
     * Endpoint for calculating the cost of a specific cart.
     * 
     * @param cart_id Long containing the id of the cart to be calculated.
     * @return ResponseEntity containing the cost of the cart.
     */
    @GetMapping("/cost/{cart_id}")
    public ResponseEntity<Double> calculateCartCost(@PathVariable("cart_id") Long cart_id) {
        return ResponseEntity.ok(oCartService.calculateCartCost(cart_id));
    }

    /*
     * Endpoint for calculating the total cost of all carts for a specific user.
     * 
     * @param user_id Long containing the id of the user to be retrieved.
     * @return ResponseEntity containing the total cost of the carts. 
     */
    @GetMapping("/totalCost/{user_id}")
    public ResponseEntity<Double> calculateTotalCartCost(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.calculateTotalCartCost(user_id));
    }

    /*
     * Endpoint for creating a new cart.
     * 
     * @param oCartEntity CartEntity object containing the cart to be created.
     * @return ResponseEntity containing the id of the created cart.
     */
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.create(oCartEntity));
    }

    /*
     * 
     */
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oCartService.populate(amount));
    }

    /*
     * Endpoint for updating an existing cart.
     * 
     * @param oCartEntity CartEntity object containing the cart to be updated.
     * @return ResponseEntity containing the updated cart.
     */
    @PutMapping("")
    public ResponseEntity<CartEntity> update(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.update(oCartEntity));
    }

    /*
     * Endpoint for deleting an existing cart.
     * 
     * @param id Long containing the id of the cart to be deleted.
     * @return ResponseEntity containing the id of the deleted cart.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.delete(id));
    }

    /*
     * 
     */
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<Long> deleteCartByUser(@PathVariable("user_id") Long user_id) {
        oCartService.deleteByUserId(user_id);
        return ResponseEntity.ok(user_id);
    }

    /*
     * Endpoint for deleting all existing carts.
     * 
     * @return ResponseEntity containing the id of the deleted cart.
     */
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCartService.empty());
    }
    
}