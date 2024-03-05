package com.example.BackendProject.models;

import java.util.ArrayList;
import java.util.List;

public class NewOrderDTO {

    private Long supermarketId;

    private List<NewOrderedItemDTO> orderedItems;

    private OrderStatus orderStatus;

    public NewOrderDTO(OrderStatus orderStatus, Long supermarketId) {
        this.orderStatus = orderStatus;
        this.supermarketId = supermarketId;
        this.orderedItems = new ArrayList<>();
    }

    public NewOrderDTO() {
    }

    public Long getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(Long supermarketId) {
        this.supermarketId = supermarketId;
    }

    public List<NewOrderedItemDTO> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<NewOrderedItemDTO> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
