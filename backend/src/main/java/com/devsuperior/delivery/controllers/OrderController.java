//Esta classe tambem pode ser chamada de resource
//Camada de Controladores REST
package com.devsuperior.delivery.controllers;

import java.util.List;

import com.devsuperior.delivery.dto.OrderDTO;
import com.devsuperior.delivery.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    //injeção de dependencia do Service
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = service.findAll();
        //retorno ResponseEntity.ok para retornar 200  e .body para definir o corpo
        return ResponseEntity.ok().body(list);
    }
}
