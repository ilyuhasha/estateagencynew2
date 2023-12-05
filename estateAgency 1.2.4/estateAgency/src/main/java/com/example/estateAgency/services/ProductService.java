package com.example.estateAgency.services;

import com.example.estateAgency.models.Product;
import com.example.estateAgency.repositories.ProductRepository;
//import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }
//    public List<Product> listProducts(String title) {
//        CriteriaBuilder builder = productRepository.getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//        Root<Product> root = query.from(Product.class);
//
//        if (title != null) {
//            query.where(builder.equal(root.get("title"), title));
//        }
//
//        return productRepository.getEntityManager().createQuery(query).getResultList();
//    }



    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


}



