package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository oCartRepository;

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
    public Page<CartEntity> getPageByUser(Long idUser, Pageable oPageable) {
        return oCartRepository.findByUser(idUser, oPageable);
    }

    // Create new cart
    public Long create(CartEntity oCartEntity) {
        oSessionService.onlyUsers();
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
    public CartEntity findByUserAndIdProduct(Long idUser, Long idProduct) {
        return oCartRepository.findByUserAndIdProduct(idUser, idProduct).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
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
