//Camada de acesso a dados
package com.devsuperior.delivery.repositories;

import java.util.List;

import com.devsuperior.delivery.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    //Ordenando o retorna da consulta
    List<Product> findAllByOrderByNameAsc();
}
