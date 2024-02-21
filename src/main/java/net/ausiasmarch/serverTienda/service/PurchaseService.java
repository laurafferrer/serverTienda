/* Service  that performs CRUD operations on the PurchaseEntity entity*/
package net.ausiasmarch.serverTienda.service;

import java.time.LocalDate;

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
import net.ausiasmarch.serverTienda.helper.PurchaseGenartionHelper;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository oPurchaseRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    CartService oCartService;

    @Autowired
    UserService oUserService;

    @Autowired
    ProductService oProductService;

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    @Autowired
    PurchaseDetailRepository oPurchaseDetailRepository;

    // Get purchase by ID
    public PurchaseEntity get(Long id) {
        return oPurchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("purchase not found"));
    }

    // Get a page of purchases
    public Page<PurchaseEntity> getPage(Pageable oPageable) {
        return oPurchaseRepository.findAll(oPageable);
    }

    // Get random purchase
    public PurchaseEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oPurchaseRepository.count()), 1);
        return oPurchaseRepository.findAll(oPageable).getContent().get(0);
    }

    // Find purchases by user Id
    public Page<PurchaseEntity> findByUserId(Long user_id, Pageable oPageable) {
        return oPurchaseRepository.findByUserId(user_id, oPageable);
    }

    // Find purchases by date order desc
    public Page<PurchaseEntity> findPurchaseByDateOrderDesc(Pageable pageable) {
        return oPurchaseRepository.findPurchaseByDateOrderDesc(pageable);
    }

    // Find purchases by date order asc
    public Page<PurchaseEntity> findPurchaseByDateOrderAsc(Pageable pageable) {
        return oPurchaseRepository.findPurchaseByDateOrderAsc(pageable);
    }

    // Find purchases by date order containing
    public Page<PurchaseEntity> findPurchaseByDateOrderContaining(String date_purchase, Pageable pageable) {
        return oPurchaseRepository.findPurchaseByDateOrderContaining(date_purchase, pageable);
    }

    // Create a new purchase
    public PurchaseEntity create(PurchaseEntity oPurchaseEntity) {
        //oSessionService.onlyAdmins();

        // Validation num of bill are more than 0
        if (oPurchaseEntity.getNum_bill() <= 0) {
            throw new IllegalArgumentException("The num of bill must be more than 0");
        }

        // Validation date of bill are before or current date
        LocalDate currentDate = LocalDate.now();
        LocalDate date_bill = oPurchaseEntity.getDate_bill();

        if (date_bill == null || date_bill.isAfter(currentDate)) {
            throw new IllegalArgumentException("The date of bill must be before or current date");
        }

        oPurchaseEntity.setId(null);
        return oPurchaseRepository.save(oPurchaseEntity);
    }

    // Update an existing purchase
    public PurchaseEntity update(PurchaseEntity oPurchaseEntity) {
        //oSessionService.onlyAdmins();
        return oPurchaseRepository.save(oPurchaseEntity);
    }

    // Delete an existing purchase
    public Long delete(Long id) {
        //oSessionService.onlyAdmins();
        oPurchaseRepository.deleteById(id);
        return id;
    }

    // Populate the product Purchase
    public Long populate(int amount) {
        //oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            UserEntity oUserEntity = oUserService.getOneRandom();
            LocalDate date_purchase = PurchaseGenartionHelper.getRandomDatePurchase();
            Long num_bill = PurchaseGenartionHelper.getRandomNumBill();
            LocalDate date_bill = PurchaseGenartionHelper.getRandomDateBill();
            oPurchaseRepository.save(new PurchaseEntity(date_purchase, num_bill, date_bill, oUserEntity));
        }
        return oPurchaseRepository.count();
    }

    // Empty the product Purchase
    public Long empty() {
        oPurchaseRepository.deleteAll();
        oPurchaseRepository.resetAutoIncrement();
        oPurchaseRepository.flush();
        return oPurchaseRepository.count();
    }

    @Transactional
    public PurchaseEntity makeProductPurchase(ProductEntity oProductEntity, UserEntity oUserEntity, int amount) {
        //oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());
        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setUser(oUserEntity);
        oPurchaseEntity.setNum_bill(PurchaseGenartionHelper.getRandomNumBill());
        oPurchaseEntity.setDate_purchase(LocalDate.now());

        oPurchaseRepository.save(oPurchaseEntity);

        PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
        oPurchaseDetailEntity.setId(null);
        oPurchaseDetailEntity.setProduct(oProductEntity);
        oPurchaseDetailEntity.setPurchase(oPurchaseEntity);
        oPurchaseDetailEntity.setAmount(amount);
        oPurchaseDetailEntity.setPrice(oProductEntity.getPrice());

        oPurchaseDetailRepository.save(oPurchaseDetailEntity);

        oProductService.updateStock(oProductEntity, amount);

        return oPurchaseEntity;
    }

    @Transactional
    public PurchaseEntity makePurchaseSingleCart(CartEntity oCartEntity, UserEntity oUserEntity) {

        //oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setUser(oUserEntity);
        oPurchaseEntity.setDate_purchase(LocalDate.now());
        oPurchaseEntity.setNum_bill(PurchaseGenartionHelper.getRandomNumBill());

        oPurchaseRepository.save(oPurchaseEntity);

        PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
        oPurchaseDetailEntity.setId(null);
        oPurchaseDetailEntity.setAmount(oCartEntity.getAmount());
        oPurchaseDetailEntity.setPrice(oCartEntity.getProduct().getPrice());
        oPurchaseDetailEntity.setProduct(oCartEntity.getProduct());
        oPurchaseDetailEntity.setPurchase(oPurchaseEntity);

        oPurchaseDetailRepository.save(oPurchaseDetailEntity);

        ProductEntity product = oCartEntity.getProduct();
        oProductService.updateStock(product, oCartEntity.getAmount());

        oCartService.delete(oCartEntity.getId());

        return oPurchaseEntity;
    }

    @Transactional
    public PurchaseEntity makePurchaseAllCarts (Page<CartEntity> oCartsEntity, UserEntity oUserEntity) {
        
        //oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setUser(oUserEntity);

        oPurchaseEntity.setDate_purchase(LocalDate.now());

        oPurchaseEntity.setNum_bill(PurchaseGenartionHelper.getRandomNumBill());
        oPurchaseEntity.setDate_bill(LocalDate.now());

        oPurchaseRepository.save(oPurchaseEntity);

        oCartsEntity = oCartService.getCartByUser(oUserEntity.getId(), PageRequest.of(0, 1000));
        
        for (CartEntity cart: oCartsEntity) {
            PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
            oPurchaseDetailEntity.setId(null);
            oPurchaseDetailEntity.setAmount(cart.getAmount());
            oPurchaseDetailEntity.setPrice(cart.getProduct().getPrice());
            oPurchaseDetailEntity.setProduct(cart.getProduct());
            oPurchaseDetailEntity.setPurchase(oPurchaseEntity);

            oPurchaseDetailRepository.save(oPurchaseDetailEntity);
            ProductEntity product = cart.getProduct();
            oProductService.updateStock(product, cart.getAmount());
        }

        oCartService.deleteByUserId(oUserEntity.getId());

        return oPurchaseEntity;

    }

    public Long cancelPurchase(Long id) {
        //PurchaseEntity purchase = oPurchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: Purchase not found."));
        //oSessionService.onlyAdminsOrUsersWithTheirData(purchase.getUser().getId());
        if (oPurchaseRepository.existsById(id)) {
            Page<PurchaseDetailEntity> purchasesDetail = oPurchaseDetailRepository.findByPurchaseId(id, PageRequest.of(0, 1000));
            for (PurchaseDetailEntity purchaseDetail : purchasesDetail) {
                ProductEntity product = purchaseDetail.getProduct();
                int amount = purchaseDetail.getAmount();
                oProductService.updateStock(product, -amount);
            }
            oPurchaseDetailRepository.deleteAll(purchasesDetail);
            oPurchaseRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: Purchase not found.");
        }
    }

}
