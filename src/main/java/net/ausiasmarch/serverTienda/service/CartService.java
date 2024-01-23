package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository oCartRepository;

    @Autowired
    ProductService oProductService;

    @Autowired
    UserService oUserService;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get cart by ID
    public CartEntity get(Long id) {
        return oCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get cart by user id
    public List<CartEntity> getByUser(Long user_id) {
        return oCartRepository.findByUser_Id(user_id);
    }

    // Get all carts for a specific user
    public List<CartEntity> getAllByIdUser(Long user_id) {
        return oCartRepository.findAllByUser_Id(user_id);
    }

    // Get cart by user id and product id
    public CartEntity getByUserAndProduct(Long user_id, Long product_id) {
        return oCartRepository.findByUser_IdAndProduct_Id(user_id, product_id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get a page or carts
    public Page<CartEntity> getPage(Pageable oPageable) {
        return oCartRepository.findAll(oPageable);
    }

    // Create new cart with validation
    public Long create(CartEntity oCartEntity) {
        UserEntity oUserEntity = oUserService.get(oCartEntity.getUser().getId());
        ProductEntity oProductEntity = oProductService.get(oCartEntity.getProduct().getId());

        Optional<CartEntity> cartFromDatabase = oCartRepository.findByUser_IdAndProduct_Id(oUserEntity.getId(), oProductEntity.getId());
        if (cartFromDatabase.isPresent()) {
            CartEntity cart = cartFromDatabase.get();
            cart.setAmount(cart.getAmount() + oCartEntity.getAmount());
            return oCartRepository.save(oCartEntity).getId();
        } else {
            oCartEntity.setId(null);
            oCartEntity.setUser(oUserEntity);
            oCartEntity.setProduct(oProductEntity);
            return oCartRepository.save(oCartEntity).getId();
        }
    }

    // Update existing cart
    public CartEntity update(CartEntity oCartEntity) {
        CartEntity oCartEntityFromDatabase = this.get(oCartEntity.getId());
        oCartEntity.setUser(oCartEntityFromDatabase.getUser());
        oCartEntity.setProduct(oCartEntityFromDatabase.getProduct());

        return oCartRepository.save(oCartEntity);
    }

    // Delete existing cart by ID
    public Long delete(Long id) {
        oSessionService.onlyUsers();
        oCartRepository.deleteById(id);
        return id;
    }

    // Delete all cart items for a specific user
    public void deleteByUser(Long user_id) {
        oCartRepository.findAllByUser_Id(user_id);
    }

    // Empty the cart table
    public Long emptyTable() {
        oCartRepository.deleteAll();
        oCartRepository.resetAutoIncrement();
        oCartRepository.resetAutoIncrement();
        return oCartRepository.count();
    }

}
