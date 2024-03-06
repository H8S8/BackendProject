package com.example.BackendProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "product_type")
    private ProductType productType;

    @JsonIgnoreProperties({"item"})
    @OneToMany(mappedBy = "item")
    private List<Stock> stocks;

    public Item(String name, ProductType productType){
        this.name = name;
        this.productType = productType;
        this.stocks = new ArrayList<Stock>();
    }

    public Item(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
