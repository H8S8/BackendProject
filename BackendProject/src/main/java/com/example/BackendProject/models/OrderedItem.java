package com.example.BackendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties ({"orderedItems"})
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnoreProperties ({"orderedItems"})
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column
    private int orderQuantity;

    public OrderedItem(Order order, Stock stock, int orderQuantity) {
        this.order = order;
        this.stock = stock;
        this.orderQuantity = orderQuantity;
    }

    public OrderedItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    //@Transactional Method to remove stock in Service Class - tbd
}
