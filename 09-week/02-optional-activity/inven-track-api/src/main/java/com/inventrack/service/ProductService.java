package com.inventrack.service;

import com.inventrack.entity.Product;
import com.inventrack.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product with id " + id + " not found"));
    }

    public Product create(Product data) {
        Product product = new Product();
        copy(data, product);
        return repository.save(product);
    }

    public Product update(Long id, Product data) {
        Product product = findById(id);  
        copy(data, product);
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.delete(findById(id)); 
    }

    private void copy(Product from, Product to) {
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setPrice(from.getPrice());
        to.setStock(from.getStock());
    }
}