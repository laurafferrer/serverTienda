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

    // Create new cart
    
    
}
