package com.devsuperior.delivery.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.delivery.entities.Order;
import com.devsuperior.delivery.entities.OrderStatus;

public class OrderDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    //Atributos
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;
    //para carregar a lista de produtos
    private List<ProductDTO> products = new ArrayList<>();
    
    //Construtores
    public OrderDTO(){}

    public OrderDTO(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moment = moment;
        this.status = status;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        address = entity.getAddress();
        latitude = entity.getLatitude();
        longitude = entity.getLongitude();
        moment = entity.getMoment();
        status = entity.getStatus();
        products = entity.getProducts().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Instant getMoment() {
        return moment;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<ProductDTO> getProducts() {
        return products;
    }

    //toString
    @Override
    public String toString() {
        return "OrderDTO [address=" + address + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude
                + ", moment=" + moment + ", products=" + products + ", status=" + status + "]";
    }

    

    
    
}
