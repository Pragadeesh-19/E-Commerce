package org.pragadeesh.ecommerce.service;

import jakarta.annotation.Resource;
import org.pragadeesh.ecommerce.model.Product;
import org.pragadeesh.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> productById(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public Product updateProduct(UUID uuid, Product product) throws Exception {
        Optional<Product> existingProductOpt = productRepository.findById(uuid);

        if(existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if(product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if(product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }
            if(product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if(product.getStock() != null) {
                existingProduct.setStock(product.getStock());
            }

            return productRepository.save(existingProduct);
        } else {
            throw new Exception("Product not found with id " + uuid);
        }
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
