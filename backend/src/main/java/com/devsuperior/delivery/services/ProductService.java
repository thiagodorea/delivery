//Camada de Serviço
package com.devsuperior.delivery.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.delivery.dto.ProductDTO;
import com.devsuperior.delivery.entities.Product;
import com.devsuperior.delivery.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameAsc();
        //transformar a lista de produtos em produtos dto
        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }
}
