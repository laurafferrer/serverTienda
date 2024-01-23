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

import net.ausiasmarch.serverTienda.entity.OrderingEntity;
import net.ausiasmarch.serverTienda.service.OrderingService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/ordering")
public class OrderingApi {

    @Autowired
    OrderingService oOrderingService;

    // Get ordering by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderingEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderingService.get(id));
    }

    // Get all orderings
    @GetMapping("")
    public ResponseEntity<Page<OrderingEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oOrderingService.getPage(oPageable));
    }

    // Get ordering by user_id
    @GetMapping("/byuser_id/{user_id}")
    public ResponseEntity<Page<OrderingEntity>> findByUserId(
            Pageable oPageable,
            @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oOrderingService.findByUserId(user_id, oPageable));
    }

    // Create new ordering
    @PostMapping("")
    public ResponseEntity<OrderingEntity> create(@RequestBody OrderingEntity oOrderingEntity) {
        return ResponseEntity.ok(oOrderingService.create(oOrderingEntity));
    }

    // Update existing ordering
    @PutMapping("")
    public ResponseEntity<OrderingEntity> update(@RequestBody OrderingEntity oOrderingEntity) {
        return ResponseEntity.ok(oOrderingService.update(oOrderingEntity));
    }

    // Delete existing ordering
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderingService.delete(id));
    }

    // Remove all orderings
    @DeleteMapping("/emptyTable")
    public ResponseEntity<Long> emptyTable() {
        return ResponseEntity.ok(oOrderingService.emptyTable());
    }

    // Get orders by date order desc
    @GetMapping("/bydate_orderDesc")
    public ResponseEntity<Page<OrderingEntity>> findOrderingBydate_orderDesc(Pageable pageable) {
        return ResponseEntity.ok(oOrderingService.findOrderingBydate_orderDesc(pageable));
    }

    // Get orders by date order asc
    @GetMapping("/bydate_orderAsc")
    public ResponseEntity<Page<OrderingEntity>> findOrderingBydate_orderAsc(Pageable pageable) {
        return ResponseEntity.ok(oOrderingService.findOrderingBydate_orderAsc(pageable));
    }

    // Get orders by date order containing
    @GetMapping("/bydate_orderContaining/{date_order}")
    public ResponseEntity<Page<OrderingEntity>> findOrderingBydate_orderContaining(
            Pageable pageable,
            @PathVariable("date_order") String date_order) {
        return ResponseEntity.ok(oOrderingService.findOrderingBydate_orderContaining(date_order, pageable));
    }     
    
}
