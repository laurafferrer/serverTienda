package net.ausiasmarch.serverTienda.service;

import java.util.Optional;

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

    // get purchase detail by Id
    public PurchaseDetailEntity get(Long id) {
        return oPurchaseDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase detail not found"));  
    }

    // get page of purchase details
    public Page <PurchaseDetailEntity> getPage(Pageable oPageable) {
        return oPurchaseDetailRepository.findAll(oPageable);
    }

    // create a new purchase detail
    public Long create (PurchaseDetailEntity oPurchaseDetailEntity) {
        oSessionService.onlyAdmins();
        oPurchaseDetailEntity.setId(null);
        return oPurchaseDetailRepository.save(oPurchaseDetailEntity).getId();
    }

    // update an existing purchase detail 
    public PurchaseDetailEntity update(PurchaseDetailEntity oPurchaseDetailEntity) {
        oSessionService.onlyAdmins();
        return oPurchaseDetailRepository.save(oPurchaseDetailEntity);
    }

    // delete an existing purchase detail by Id
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oPurchaseDetailRepository.deleteById(id);
        return id;
    }

    // Get random purchase detail
    public PurchaseDetailEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oPurchaseDetailRepository.count()), 1);
        return oPurchaseDetailRepository.findAll(oPageable).getContent().get(0);
    }

    // Find purchase details by ordering id
    public Optional<PurchaseDetailEntity> findByordering_id(Long ordering_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByordering_id(ordering_id, oPageable);
    }

    // Find purchase details by product id
    public Optional<PurchaseDetailEntity> findByproduct_id(Long product_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByproduct_id(product_id, oPageable);
    }

    // Find purchase details by ordering id and product id
    public Optional<PurchaseDetailEntity> findByordering_idAndproduct_id(Long ordering_id, Long product_id, Pageable oPageable) {
        return oPurchaseDetailRepository.findByordering_idAndproduct_id(ordering_id, product_id, oPageable);
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
    public Long emptyTable() {
        oPurchaseDetailRepository.deleteAll();
        oPurchaseDetailRepository.resetAutoIncrement();
        oPurchaseDetailRepository.flush();
        return oPurchaseDetailRepository.count();
    }
    
}
