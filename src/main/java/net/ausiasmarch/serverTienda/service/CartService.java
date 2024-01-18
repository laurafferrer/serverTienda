package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository oCartRepository;

    @Autowired
    ProductService oProductService;
    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // get cart by ID
    public CartEntity get(Long id) {
        return oCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get a page or carts
    public Page<CartEntity> getPage(Pageable oPageable) {
        return oCartRepository.findAll(oPageable);
    }

    // Get page of cart for a specific user
    public Page<CartEntity> getPageByIdUser(Long idUser, Pageable oPageable) {
        return oCartRepository.findByIdUser(idUser, oPageable);
    }

    // Create new cart with validation
    public Long create(CartEntity oCartEntity) {
        oSessionService.onlyUsers();

        // Validate amount is positive
        if (oCartEntity.getAmount() <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Validate amount are menor or equal to stock in table product
        Long idProduct = oCartEntity.getIdProduct().getId();
        ProductEntity product = oProductService.get(idProduct);
        if (oCartEntity.getAmount() > product.getStock()) {
            throw new IllegalArgumentException("Amount must be less or equal to stock");
        }

        oCartEntity.setId(null);
        return oCartRepository.save(oCartEntity).getId();
    }

    // Update existing cart
    public CartEntity update(CartEntity oCartEntity) {
        oSessionService.onlyUsers();
        return oCartRepository.save(oCartEntity);
    }

    // Delete existing cart
    public Long delete(Long id) {
        oSessionService.onlyUsers();
        oCartRepository.deleteById(id);
        return id;
    }

    // Find specific item in the cart base on user Id and product Id
    public CartEntity findByIdUserAndIdProduct(Long idUser, Long idProduct) {
        return oCartRepository.findByIdUserAndIdProduct(idUser, idProduct).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Remove all cart items for a specific user
    public void deleteByIdUser(Long idUser) {
        oCartRepository.deleteByIdUser(idUser);
    }

    // Empty the cart table
    public Long emptyTable() {
        oCartRepository.deleteAll();
        oCartRepository.resetAutoIncrement();
        oCartRepository.resetAutoIncrement();
        return oCartRepository.count();
    }

}
