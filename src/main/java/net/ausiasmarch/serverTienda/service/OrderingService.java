/* Service  that performs CRUD operations on the OrderingEntity entity*/
package net.ausiasmarch.serverTienda.service;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public Page<OrderingEntity> getPage(Pageable oPageable) {
        return oOrderingRepository.findAll(oPageable);
    }

    // Get random ordering
    public OrderingEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oOrderingRepository.count()), 1);
        return oOrderingRepository.findAll(oPageable).getContent().get(0);
    }

    // Create a new ordering
    public OrderingEntity create(OrderingEntity oOrderingEntity) {
        //oSessionService.onlyAdmins();

        // Validation num of bill are more than 0
        if (oOrderingEntity.getnum_bill() <= 0) {
            throw new IllegalArgumentException("The num of bill must be more than 0");
        }

        // Validation date of bill are before or current date 
        LocalDate currentDate = LocalDate.now();
        LocalDate date_bill = oOrderingEntity.getdate_bill();

        if (date_bill == null || date_bill.isAfter(currentDate)) {
            throw new IllegalArgumentException("The date of bill must be before or current date");
        }

        oOrderingEntity.setId(null);
        return oOrderingRepository.save(oOrderingEntity);
    }

    // Update an existing ordering
    public OrderingEntity update(OrderingEntity oOrderingEntity) {
        //oSessionService.onlyAdmins();
        return oOrderingRepository.save(oOrderingEntity);
    }

    // Delete an existing ordering
    public Long delete(Long id) {
        //oSessionService.onlyAdmins();
        oOrderingRepository.deleteById(id);
        return id;
    }

    // Find orderings by user Id
    public Page<OrderingEntity> findByUserId(Long user_id, Pageable oPageable) {
        return oOrderingRepository.findByUserId(user_id, oPageable);
    }

    // Find orderings by date order desc
    public Page<OrderingEntity> findOrderingBydate_orderDesc(Pageable pageable) {
        return oOrderingRepository.findOrderingBydate_orderDesc(pageable);
    }

    // Find orderings by date order asc
    public Page<OrderingEntity> findOrderingBydate_orderAsc(Pageable pageable) {
        return oOrderingRepository.findOrderingBydate_orderAsc(pageable);
    }

    // Find orderings by date order containing
    public Page<OrderingEntity> findOrderingBydate_orderContaining(String date_order, Pageable pageable) {
        return oOrderingRepository.findOrderingBydate_orderContaining(date_order, pageable);
    }

    // Empty the product ordering
    public Long emptyTable() {
        oOrderingRepository.deleteAll();
        oOrderingRepository.resetAutoIncrement();
        oOrderingRepository.flush();
        return oOrderingRepository.count();
    }

}
