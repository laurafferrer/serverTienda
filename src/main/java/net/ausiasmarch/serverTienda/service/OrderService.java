/* Service  that performs CRUD operations on the OrderEntity entity*/
package net.ausiasmarch.serverTienda.service;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.ausiasmarch.serverTienda.entity.OrderEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository oOrderRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get order by ID
    public OrderEntity get(Long id) {
        return oOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
    }

    // Get a page of orders
    public Page<OrderEntity> getPage(Pageable oPageable) {
        return oOrderRepository.findAll(oPageable);
    }

    // Get random order
    public OrderEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oOrderRepository.count()), 1);
        return oOrderRepository.findAll(oPageable).getContent().get(0);
    }

    // Create a new order
    public OrderEntity create(OrderEntity oOrderEntity) {
        //oSessionService.onlyAdmins();

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
    public OrderEntity update(OrderEntity oOrderEntity) {
        //oSessionService.onlyAdmins();
        return oOrderRepository.save(oOrderEntity);
    }

    // Delete an existing order
    public Long delete(Long id) {
        //oSessionService.onlyAdmins();
        oOrderRepository.deleteById(id);
        return id;
    }

    // Find orders by user Id
    public Page<OrderEntity> findByUserId(Long user_id, Pageable oPageable) {
        return oOrderRepository.findByUserId(user_id, oPageable);
    }

    // Find orders by date order desc
    public Page<OrderEntity> findOrderByDateOrderDesc(Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderDesc(pageable);
    }

    // Find orders by date order asc
    public Page<OrderEntity> findOrderByDateOrderAsc(Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderAsc(pageable);
    }

    // Find orders by date order containing
    public Page<OrderEntity> findOrderByDateOrderContaining(String date_order, Pageable pageable) {
        return oOrderRepository.findOrderByDateOrderContaining(date_order, pageable);
    }

    // Empty the product order
    public Long empty() {
        oOrderRepository.deleteAll();
        oOrderRepository.resetAutoIncrement();
        oOrderRepository.flush();
        return oOrderRepository.count();
    }

}
