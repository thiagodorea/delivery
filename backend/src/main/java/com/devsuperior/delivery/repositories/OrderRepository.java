//Camada de acesso a dados
package com.devsuperior.delivery.repositories;

import com.devsuperior.delivery.entities.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
