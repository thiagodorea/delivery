//Camada de Serviço
package com.devsuperior.delivery.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.delivery.dto.OrderDTO;
import com.devsuperior.delivery.entities.Order;
import com.devsuperior.delivery.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrdersWithProducts();
        //transformar a lista de produtos em produtos dto
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }
}
