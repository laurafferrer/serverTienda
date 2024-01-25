/* Service  that performs CRUD operations on the ProductEntity entity*/
package net.ausiasmarch.serverTienda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.transaction.Transactional;

import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository oProductRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get product by ID
    public ProductEntity get(Long id) {
        return oProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // Get a page of products
    public Page<ProductEntity> getPage(Pageable oPageable) {
        return oProductRepository.findAll(oPageable);
    }
    
    // Get random product
    public ProductEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oProductRepository.count()), 1);
        return oProductRepository.findAll(oPageable).getContent().get(0);
    }

    // Get products by category Id
    public Page<ProductEntity> getByCategory(Long category_id, Pageable oPageable) {
        return oProductRepository.findBycategory_id(category_id, oPageable);
    }

    // Get products by stock ascending
    public Page<ProductEntity> getByStockAsc(Pageable oPageable) {
        return oProductRepository.findByStockAsc(oPageable);
    }

    // Get products by price ascending
    public Page<ProductEntity> getByPriceAsc(Pageable oPageable) {
        return oProductRepository.findByPriceAsc(oPageable);
    }

    // Get products by price descending
    public Page<ProductEntity> getByPriceDesc(Pageable oPageable) {
        return oProductRepository.findByPriceDesc(oPageable);
    }

    // Get products by price ascending and category
    public Page<ProductEntity> getByPriceAscAndCategoryId(Long category_id, Pageable oPageable) {
        return oProductRepository.findByPriceAscAndCategoryid(category_id, oPageable);
    }

    // Get products by price descending and category
    public Page<ProductEntity> getByPriceDescAndCategoryId(Long category_id, Pageable oPageable) {
        return oProductRepository.findByPriceDescAndCategoryId(category_id, oPageable);
    }

    // Create new product
    public Long create(ProductEntity oProductEntity) {
        //oSessionService.onlyAdmins();
        oProductEntity.setId(null);
        return oProductRepository.save(oProductEntity).getId();
    }

    // Update existing product
    public ProductEntity update(ProductEntity oProductEntity) {
        //oSessionService.onlyAdmins();
        return oProductRepository.save(oProductEntity);
    }

    // Delete existing product
    public Long delete(Long id) {
        //oSessionService.onlyAdmins();
        oProductRepository.deleteById(id);
        return id;
    }

    // Empty the product table
    public Long empty() {
        oProductRepository.deleteAll();
        oProductRepository.resetAutoIncrement();
        oProductRepository.flush();
        return oProductRepository.count();
    }
    
    // Update stock product and check if depleted
    @Transactional
    public void updateStock(ProductEntity oProductEntity, int amount) {
        ProductEntity product = oProductRepository.findById(oProductEntity.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product != null) {
            int currentStock = product.getStock();
            int newStock = currentStock - amount;
            if (newStock < 0) {
                newStock = 0;
            }
            product.setStock(newStock);
            oProductRepository.save(product);
        }
    }

}
