package com.example.BackendProject.models;


public class NewOrderedItemDTO {

    private Long stockId;
    private Long orderId;
    private int orderQuantity;

    public NewOrderedItemDTO(Long stockId, Long orderId, int orderQuantity) {
        this.stockId = stockId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
