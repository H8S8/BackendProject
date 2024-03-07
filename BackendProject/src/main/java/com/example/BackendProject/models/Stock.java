package com.example.BackendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"stock"})
    @OneToMany(mappedBy = "stock")
    private List<OrderedItem> orderedItems;

    @JsonIgnoreProperties({"stocks"})
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column
    private int quantity;

    @Column
    private String expiryDate;

    @Column
    private int unitPrice;

    public Stock(Item item, int quantity, String expiryDate, int unitPrice) {
        this.item = item;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.orderedItems = new ArrayList<>();
        this.unitPrice = unitPrice;
    }

    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public void removeFromStock(int quantityToRemove){
        this.quantity -= quantityToRemove;
    }
}
