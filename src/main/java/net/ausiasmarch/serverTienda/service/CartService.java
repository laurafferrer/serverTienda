/* Service  responsible for performing CRUD operations on the CartEntity entity*/
package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.entity.UserEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.helper.CartGenerationHelper;
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

    // Get a cart by its ID
    public CartEntity get(Long id) {
        return oCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get carts by user ID
    public List<CartEntity> getByUser(Long user_id) {
        return oCartRepository.findByUserId(user_id);
    }

    // Get cart by user ID and product ID
    public CartEntity getByUserAndProduct(Long user_id, Long product_id) {
        return oCartRepository.findByUserIdAndProductId(user_id, product_id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get a page or carts
    public Page<CartEntity> getPage(Pageable oPageable) {
        return oCartRepository.findAll(oPageable);
    }

    // Get all carts for a specific user
    public List<CartEntity> getAllByIdUser(Long user_id) {
        //oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oCartRepository.findAllByIdUser(user_id);
    }

    // Calculate the cost of a specific cart
    public Double calculateCartCost(Long id) {
        return oCartRepository.calculateCartCost(id);
    }

    // Calculate the total cost of all carts for a specific user
    public Double calculateTotalCartCost(Long user_id) {
        return oCartRepository.calculateTotalCartCost(user_id);
    }

    // Create new cart with validation
    public Long create(CartEntity oCartEntity) {
        //oSessionService.onlyAdminsOrUsersWithTheirData(oCartEntity.getUser().getId();
        UserEntity oUserEntity = oUserService.get(oCartEntity.getUser().getId());
        ProductEntity oProductEntity = oProductService.get(oCartEntity.getProduct().getId());

        Optional<CartEntity> cartFromDatabase = oCartRepository.findByUserIdAndProductId(oUserEntity.getId(), oProductEntity.getId());
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

    // Populate the cart table with random data
    public Long populate(Integer amount) {
        //oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            int amountInCart = CartGenerationHelper.getRandomAmount();
            UserEntity randomUser = oUserService.getOneRandom();
            ProductEntity randomProduct = oProductService.getOneRandom();
            oCartRepository.save(new CartEntity(amountInCart, randomUser, randomProduct));
        }
        return oCartRepository.count();
    }

    // Update an existing cart
    public CartEntity update(CartEntity oCartEntity) {
        CartEntity oCartEntityFromDatabase = this.get(oCartEntity.getId());
        //oSessionService.onlyAdminsOrUsersWithTheirData(oCartEntityFromDatabase.getUser().getId());
        oCartEntity.setUser(oCartEntityFromDatabase.getUser());
        oCartEntity.setProduct(oCartEntityFromDatabase.getProduct());

        return oCartRepository.save(oCartEntity);
    }

    // Delete an existing cart by ID
    public Long delete(Long id) {
        //CartEntity oCartEntityFromDatabase = this.get(id);
        //oSessionService.onlyAdminsOrUsersWithTheirData(oCartEntityFromDatabase.getUser().getId());
        if (oCartRepository.existsById(id)) {
            oCartRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: El cart not found.");
        }
    }

    // Remove all carts for a specific user
    @Transactional
    public void deleteByUserId(Long user_id) {
        //oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        oCartRepository.deleteByUserId(user_id);
    }

    // Empty the cart table
    public Long empty() {
        oCartRepository.deleteAll();
        oCartRepository.resetAutoIncrement();
        oCartRepository.resetAutoIncrement();
        return oCartRepository.count();
    }

}
