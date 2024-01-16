package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import net.ausiasmarch.serverTienda.entity.OrderingEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.OrderingRepository;

@Service
public class OrderingService {

    @Autowired
    OrderingRepository oOrderingRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get ordering by ID
    public OrderingEntity get(Long id) {
        return oOrderingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ordering not found"));
    }

    // Get a page of orderings
    public Page<OrderingEntity> getPage(int page, int size) {
        return oOrderingRepository.findAll(PageRequest.of(page, size));
    }

    // Create a new ordering
    public OrderingEntity create(OrderingEntity oOrderingEntity) {
        oSessionService.onlyAdmins();
        oOrderingEntity.setId(null);
        return oOrderingRepository.save(oOrderingEntity);
    }

    // Update an existing ordering
    public OrderingEntity update(OrderingEntity oOrderingEntity) {
        oSessionService.onlyAdmins();
        return oOrderingRepository.save(oOrderingEntity);
    }

    // Delete an existing ordering
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oOrderingRepository.deleteById(id);
        return id;
    }

    // Get random ordering
    public OrderingEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oOrderingRepository.count()), 1);
        return oOrderingRepository.findAll(oPageable).getContent().get(0);
    }

    // Find orderings by user Id
    public Page<OrderingEntity> findByIdUser(Long idUser, Pageable oPageable) {
        return oOrderingRepository.findByIdUser(idUser, oPageable);
    }

    // Find orderings by date order desc
    public Page<OrderingEntity> findOrderingByDateOrderDesc(Pageable pageable) {
        return oOrderingRepository.findOrderingByDateOrderDesc(pageable);
    }

    // Find orderings by date order asc
    public Page<OrderingEntity> findOrderingByDateOrderAsc(Pageable pageable) {
        return oOrderingRepository.findOrderingByDateOrderAsc(pageable);
    }

    // Find orderings by date order containing
    public Page<OrderingEntity> findOrderingByDateOrderContaining(String dateOrder, Pageable pageable) {
        return oOrderingRepository.findOrderingByDateOrderContaining(dateOrder, pageable);
    }

    // Empty the product ordering
    public Long emptyTable() {
        oOrderingRepository.deleteAll();
        oOrderingRepository.resetAutoIncrement();
        oOrderingRepository.flush();
        return oOrderingRepository.count();
    }

}
