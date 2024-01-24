package net.ausiasmarch.serverTienda.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.serverTienda.entity.OrderEntity;
import net.ausiasmarch.serverTienda.service.OrderService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderService oOrderService;

    // Get order by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderService.get(id));
    }

    // Get all orders
    @GetMapping("")
    public ResponseEntity<Page<OrderEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oOrderService.getPage(oPageable));
    }

    // Get order by user_id
    @GetMapping("/byuser_id/{user_id}")
    public ResponseEntity<Page<OrderEntity>> findByUserId(
            Pageable oPageable,
            @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oOrderService.findByUserId(user_id, oPageable));
    }

    // Create new order
    @PostMapping("")
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity oOrderEntity) {
        return ResponseEntity.ok(oOrderService.create(oOrderEntity));
    }

    // Update existing order
    @PutMapping("")
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity oOrderEntity) {
        return ResponseEntity.ok(oOrderService.update(oOrderEntity));
    }

    // Delete existing order
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderService.delete(id));
    }

    // Remove all orders
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oOrderService.empty());
    }

    // Get orders by date order desc
    @GetMapping("/byDateOrderDesc")
    public ResponseEntity<Page<OrderEntity>> findOrderByDateOrderDesc(Pageable pageable) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderDesc(pageable));
    }

    // Get orders by date order asc
    @GetMapping("/byDateOrderAsc")
    public ResponseEntity<Page<OrderEntity>> findOrderByDateOrderAsc(Pageable pageable) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderAsc(pageable));
    }

    // Get orders by date order containing
    @GetMapping("/byDateOrderContaining/{date_order}")
    public ResponseEntity<Page<OrderEntity>> findOrderByDateOrderContaining(
            Pageable pageable,
            @PathVariable("date_order") String date_order) {
        return ResponseEntity.ok(oOrderService.findOrderByDateOrderContaining(date_order, pageable));
    }     
    
}
