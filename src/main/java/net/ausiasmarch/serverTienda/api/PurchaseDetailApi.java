package net.ausiasmarch.serverTienda.api;

import java.util.Optional;

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

import net.ausiasmarch.serverTienda.service.PurchaseDetailService;
import net.ausiasmarch.serverTienda.entity.PurchaseDetailEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/purchaseDetail")
public class PurchaseDetailApi {

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    // Get purchase detail by id
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDetailEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.get(id));
    }

    // Get all purchase details
    @GetMapping("")
    public ResponseEntity<Page<PurchaseDetailEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oPurchaseDetailService.getPage(oPageable));
    }

    // Create new purchase detail
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.create(oPurchaseDetailEntity));
    }

    // Update existing purchase detail
    @PutMapping("")
    public ResponseEntity<PurchaseDetailEntity> update(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.update(oPurchaseDetailEntity));
    }

    // Delete existing purchase detail
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.delete(id));
    }

    // Get purchase detail by idOrdering
    @GetMapping("/byIdOrdering/{idOrdering}")
    public ResponseEntity<Optional<PurchaseDetailEntity>> findByIdOrdering(
            Pageable oPageable,
            @PathVariable("idOrdering") Long idOrdering) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdOrdering(idOrdering, oPageable));
    }

    // Get purchase detail by idProduct
    @GetMapping("/byIdProduct/{idProduct}")
    public ResponseEntity<Optional<PurchaseDetailEntity>> findByIdProduct(
            Pageable oPageable,
            @PathVariable("idProduct") Long idProduct) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdProduct(idProduct, oPageable));
    }

    // Get purchase detail by idOrdering and idProduct
    @GetMapping("/byIdOrderingAndIdProduct/{idOrdering}/{idProduct}")
    public ResponseEntity<Optional<PurchaseDetailEntity>> findByIdOrderingAndIdProduct(
            Pageable oPageable,
            @PathVariable("idOrdering") Long idOrdering,
            @PathVariable("idProduct") Long idProduct) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdOrderingAndIdProduct(idOrdering, idProduct, oPageable));
    }

    // Get purchase dewtail by price desc
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceDesc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceDesc(oPageable));
    }

    // Get purchase detail by price asc
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceAsc(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceAsc(oPageable));
    }

    // Empty the purchase detail table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> emptyTable() {
        return ResponseEntity.ok(oPurchaseDetailService.emptyTable());
    }
    
}
