/* Service  that performs CRUD operations on the PurchaseDetailEntity entity*/
package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.PurchaseDetailRepository;

@Service
public class PurchaseDetailService {

    @Autowired
    PurchaseDetailRepository oPurchaseDetailRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get purchase detail by Id
    public PurchaseDetailEntity get(Long id) {
        return oPurchaseDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase detail not found"));  
    }

    // Get page of purchase details
    public Page <PurchaseDetailEntity> getPage(Pageable oPageable) {
        return oPurchaseDetailRepository.findAll(oPageable);
    }

    // Create a new purchase detail
    public Long create (PurchaseDetailEntity oPurchaseDetailEntity) {
        //oSessionService.onlyAdmins();
        oPurchaseDetailEntity.setId(null);
        return oPurchaseDetailRepository.save(oPurchaseDetailEntity).getId();
    }

    // Update an existing purchase detail 
    public PurchaseDetailEntity update(PurchaseDetailEntity oPurchaseDetailEntity) {
        //oSessionService.onlyAdmins();
        return oPurchaseDetailRepository.save(oPurchaseDetailEntity);
    }

    // Delete an existing purchase detail by Id
    public Long delete(Long id) {
        //oSessionService.onlyAdmins();
        oPurchaseDetailRepository.deleteById(id);
        return id;
    }

    // Get random purchase detail
    public PurchaseDetailEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oPurchaseDetailRepository.count()), 1);
        return oPurchaseDetailRepository.findAll(oPageable).getContent().get(0);
    }

    // Find purchase details by purchase id
    public Page<PurchaseDetailEntity> findByPurchaseId(Long purchase_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByPurchaseId(purchase_id, oPageable);
    }

    // Find purchase details by product id
    public Page<PurchaseDetailEntity> findByProductId(Long product_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByProductId(product_id, oPageable);
    }

    // Find purchase details by order id and product id
    public Page<PurchaseDetailEntity> findByPurchaseIdAndProductId(Long purchase_id, Long product_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByPurchaseIdAndProductId(purchase_id, product_id, oPageable);
    }

    // Find and order purchase details by price in descending order
    public Page <PurchaseDetailEntity> findAllByPriceDesc(Pageable oPageable) {
        return oPurchaseDetailRepository.findAllByPriceDesc(oPageable);
    }

    // Find and order purchase details by price in ascending order
    public Page <PurchaseDetailEntity> findAllByPriceAsc(Pageable oPageable) {
        return oPurchaseDetailRepository.findAllByPriceAsc(oPageable);
    }

    // Empty the purchase detail table
    public Long empty() {
        oPurchaseDetailRepository.deleteAll();
        oPurchaseDetailRepository.resetAutoIncrement();
        oPurchaseDetailRepository.flush();
        return oPurchaseDetailRepository.count();
    }
    
}
