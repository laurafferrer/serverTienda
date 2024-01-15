package net.ausiasmarch.serverTienda.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.serverTienda.entity.CategoryEntity;
import net.ausiasmarch.serverTienda.entity.OrderingEntity;
import net.ausiasmarch.serverTienda.entity.ProductEntity;
import net.ausiasmarch.serverTienda.exception.ResourceNotFoundException;
import net.ausiasmarch.serverTienda.repository.CategoryRepository;
import net.ausiasmarch.serverTienda.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository oProductRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    CategoryService oCategoryService;

    @Autowired
    CategoryRepository oCategoryRepository;

    @Autowired
    SessionService oSessionService;

    public ProductEntity get(Long id) {
        return oProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Page<ProductEntity> getPage(Pageable oPageable, String filter, Long idCategory) {
        Page<ProductEntity> oPage;

        if (idCategory != null && idCategory != 0) {
            if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
                oPage = oProductRepository.findByIdCategory(idCategory);
            } else {
                oPage = oProductRepository.findByName(filter);
            }
        } else {
            if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
                oPage = oProductRepository.findAll(oPageable);
            } else {
                oPage = oProductRepository.findByName(filter);
            }
        }
        return oPage;
    }

    /* NO ENTIENDO!!!
    public Page<OrderingEntity> getPageByPurchaseDetailDesc(Pageable oPageable) {
        Page<OrderingEntity> oPage;

        oPage = oProductRepository.findProductByPurchaseDetailDesc(oPageable);

        return oPage;
    }
    */

    /* NI IDEA SI ESTA BIEN :( */
    public Long create(ProductEntity productEntity) {
        // Asegurarse de que el ID sea nulo para garantizar la creación de un nuevo producto
        productEntity.setId(null);
    
        // Verificar los permisos del usuario actual a través del servicio de sesión
        oSessionService.onlyAdminsOrUsers();
    
        // Verificar si se ha establecido una categoría en la sesión
        if (!oSessionService.isCategory()) {
            // Si no hay una categoría en la sesión y el producto no tiene una categoría válida,
            // establecer la categoría de la sesión en el producto
            if (productEntity.getCategory() == null || productEntity.getCategory().getId() == null) {
                // Convertir el identificador de la sesión a Long
                Long sessionCategoryId = Long.parseLong(oSessionService.getSessionCategory());
    
                // Obtener la categoría a partir del identificador convertido
                CategoryEntity sessionCategory = (CategoryEntity) oCategoryRepository.findById(sessionCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    
                // Establecer la categoría en el producto
                productEntity.setCategory(sessionCategory);
            }
        }
    
        // Guardar el producto en el repositorio y devolver su ID
        return oProductRepository.save(productEntity).getId();
    }
    

    /* FALTA UPDATE, DELETE POPULATE, GETRANDOM Y EMPTY */
    

}
