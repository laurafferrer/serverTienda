/* Service  that performs CRUD operations on the OrderEntity entity*/
package net.ausiasmarch.serverTienda.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.ausiasmarch.serverTienda.entity.CartEntity;
import net.ausiasmarch.serverTienda.entity.PurchaseEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;
import net.ausiasmarch.serverTienda.entity.UserEntity;

import net.ausiasmarch.serverTienda.repository.PurchaseRepository;
import net.ausiasmarch.serverTienda.repository.PurchaseDetailRepository;

import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository oOrderRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    CartService oCartService;

    @Autowired
    ProductService oProductService;

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    @Autowired
    PurchaseDetailRepository oPurchaseDetailRepository;

    // Get order by ID
    public PurchaseEntity get(Long id) {
        return oOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
    }

    // Get a page of orders
    public Page<PurchaseEntity> getPage(Pageable oPageable) {
        return oOrderRepository.findAll(oPageable);
    }

    // Get random order
    public PurchaseEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oOrderRepository.count()), 1);
        return oOrderRepository.findAll(oPageable).getContent().get(0);
    }

    // Create a new order
    public PurchaseEntity create(PurchaseEntity oOrderEntity) {
        // oSessionService.onlyAdmins();

        // Validation num of bill are more than 0
        if (oOrderEntity.getNum_bill() <= 0) {
            throw new IllegalArgumentException("The num of bill must be more than 0");
        }

        // Validation date of bill are before or current date
        LocalDate currentDate = LocalDate.now();
        LocalDate date_bill = oOrderEntity.getDate_bill();

        if (date_bill == null || date_bill.isAfter(currentDate)) {
            throw new IllegalArgumentException("The date of bill must be before or current date");
        }

        oOrderEntity.setId(null);
        return oOrderRepository.save(oOrderEntity);
    }

    // Update an existing order
    public PurchaseEntity update(PurchaseEntity oOrderEntity) {
        // oSessionService.onlyAdmins();
        return oOrderRepository.save(oOrderEntity);
    }

    // Delete an existing order
    public Long delete(Long id) {
        // oSessionService.onlyAdmins();
        oOrderRepository.deleteById(id);
        return id;
    }

    // Find orders by user Id
    public Page<PurchaseEntity> findByUserId(Long user_id, Pageable oPageable) {
        return oOrderRepository.findByUserId(user_id, oPageable);
    }

    // Find orders by date order desc
    public Page<PurchaseEntity> findOrderByDateOrderDesc(Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderDesc(pageable);
    }

    // Find orders by date order asc
    public Page<PurchaseEntity> findOrderByDateOrderAsc(Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderAsc(pageable);
    }

    // Find orders by date order containing
    public Page<PurchaseEntity> findOrderByDateOrderContaining(String date_order, Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderContaining(date_order, pageable);
    }

    // Empty the product order
    public Long empty() {
        oOrderRepository.deleteAll();
        oOrderRepository.resetAutoIncrement();
        oOrderRepository.flush();
        return oOrderRepository.count();
    }

    /**
     * Generates a unique Long code for a bill based on the current date and a UUID.
     * 
     * @return A unique Long code for a bill.
     */
    public Long generateCodeBill() {
        // Format the current date in the pattern "yyyyMMdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Long actualDate = Long.parseLong(LocalDate.now().format(formatter));

        // Generate a UUID, remove hyphens, and take the first 4 characters
        Long uuid = Long.parseLong(UUID.randomUUID().toString().replace("-", "").substring(0, 4));

        // Concatenate the formatted date and UUID to create the final Long code
        return actualDate * 10000 + uuid;
    }

    @Transactional
    public PurchaseEntity makePurchaseSingleCart(CartEntity oCartEntity, UserEntity oUserEntity) {

        // oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oOrderEntity = new PurchaseEntity();

        oOrderEntity.setUser(oUserEntity);
        oOrderEntity.setDate_order(LocalDate.now());
        oOrderEntity.setNum_bill(generateCodeBill());

        oOrderRepository.save(oOrderEntity);

        PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
        oPurchaseDetailEntity.setId(null);
        oPurchaseDetailEntity.setAmount(oCartEntity.getAmount());
        oPurchaseDetailEntity.setPrice(oCartEntity.getPrice());
        oPurchaseDetailEntity.setProduct(oCartEntity.getProduct());
        oPurchaseDetailEntity.setOrder(oOrderEntity);

        oPurchaseDetailRepository.save(oPurchaseDetailEntity);

        ProductEntity product = oCartEntity.getProduct();
        oProductService.updateStock(product, oCartEntity.getAmount());

        oCartService.delete(oCartEntity.getId());

        return oOrderEntity;
    }

    @Transactional
    public PurchaseEntity makePurchaseAllCarts (List<CartEntity> carts, UserEntity oUserEntity) {
        
        // oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oOrderEntity = new PurchaseEntity();

        oOrderEntity.setUser(oUserEntity);
        oOrderEntity.setDate_order(LocalDate.now());
        oOrderEntity.setNum_bill(generateCodeBill());

        oOrderRepository.save(oOrderEntity);

        carts = oCartService.getByUser(oUserEntity.getId());
        
        for (CartEntity cart: carts) {
            PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
            oPurchaseDetailEntity.setId(null);
            oPurchaseDetailEntity.setAmount(cart.getAmount());
            oPurchaseDetailEntity.setPrice(cart.getPrice());
            oPurchaseDetailEntity.setProduct(cart.getProduct());
            oPurchaseDetailEntity.setOrder(oOrderEntity);

            oPurchaseDetailRepository.save(oPurchaseDetailEntity);
        }

        for (CartEntity cart : carts) {
            ProductEntity product = cart.getProduct();
            oProductService.updateStock(product, cart.getAmount());
        }

        oCartService.deleteByUser(oUserEntity.getId());

        return oOrderEntity;

    }

    public Long cancelOrder(Long id) {
        PurchaseEntity purchase = oOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: Order not found."));
        //oSessionService.onlyAdminsOrUsersWithTheirData(purchase.getUser().getId());
        if (oOrderRepository.existsById(id)) {
            Page<PurchaseDetailEntity> purchasesDetail = oPurchaseDetailRepository.findByOrderId(id, PageRequest.of(0, 1000));
            for (PurchaseDetailEntity purchaseDetail : purchasesDetail) {
                ProductEntity product = purchaseDetail.getProduct();
                int amount = purchaseDetail.getAmount();
                oProductService.updateStock(product, -amount);
            }
            oPurchaseDetailRepository.deleteAll(purchasesDetail);
            oOrderRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: Order not found.");
        }
    }

}
