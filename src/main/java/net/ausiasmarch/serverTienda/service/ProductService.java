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
import net.ausiasmarch.serverTienda.helper.ProductGenerationHelper;
import net.ausiasmarch.serverTienda.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository oProductRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    CategoryService oCategoryService;

    // Get product by ID
    public ProductEntity get(Long id) {
        return oProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // Get a page of products
    public Page<ProductEntity> getPage(Pageable oPageable, Long category_id, String filter) {
        if (category_id != null) {
            return oProductRepository.findByCategoryId(category_id, oPageable);
        } else {
            Page<ProductEntity> page;

            if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
                page = oProductRepository.findAll(oPageable);
            } else {
                page = oProductRepository.findByName(filter, oPageable);
            }
            return page;
        }
    }
    
    // Get random product
    public ProductEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oProductRepository.count()), 1);
        return oProductRepository.findAll(oPageable).getContent().get(0);
    }

    // Get products by category Id
    public Page<ProductEntity> getByCategoryId(Long category_id, Pageable oPageable) {
        return oProductRepository.findByCategoryId(category_id, oPageable);
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

    // Crear nuevo producto
    public Long create(ProductEntity oProductEntity) {
        // Asegurarse de que category está presente y tiene un id válido
        if (oProductEntity.getCategory() == null || oProductEntity.getCategory().getId() == null) {
            // Manejar el caso en el que la categoría no está establecida correctamente
            throw new IllegalArgumentException("La categoría no está establecida correctamente en el producto.");
        }

        // Establecer category con el objeto CategoryEntity
        oProductEntity.setCategory(oProductEntity.getCategory());

        // Asegurarse de que el id del producto sea nulo para que se genere uno nuevo
        oProductEntity.setId(null);

        // Guardar el producto en la base de datos
        return oProductRepository.save(oProductEntity).getId();
    }

    // Populate the database with random products
    public Long populate(Long amount) {
        //oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String name = ProductGenerationHelper.getRandomName();
            String description = ProductGenerationHelper.getRandomDescription();
            Double price = ProductGenerationHelper.getRandomPrice();
            Integer stock = ProductGenerationHelper.getRandomStock();
            String image = ProductGenerationHelper.getRandomImage();
            oProductRepository.save(new ProductEntity(name, description, price, stock, image, oCategoryService.getOneRandom()));
        }
        return oProductRepository.count();
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
    @Transactional
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
