package com.example.BackendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column
    private OrderStatus orderStatus;

    @JsonIgnoreProperties({"order"})
    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;

    @JsonIgnoreProperties({"orders"})
    @ManyToOne
    @JoinColumn(name = "supermarket_id")
    private Supermarket supermarket;


    public Order(OrderStatus orderStatus, Supermarket supermarket) {
        this.orderStatus = orderStatus;
        this.supermarket = supermarket;
        this.orderedItems = new ArrayList<>();
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int calculateOrderCost(){
        int totalCost = 0;
        for(OrderedItem orderedItem : this.orderedItems){
            totalCost += orderedItem.getStock().getUnitPrice() * orderedItem.getOrderQuantity();
        }
        return totalCost;
    }
}
