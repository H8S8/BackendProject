package com.example.BackendProject.models;


public class NewOrderedItemDTO {

    private Long orderItemId;
    private Long orderId;
    private int orderQuantity;

    public NewOrderedItemDTO(Long orderItemId, Long orderId, int orderQuantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }

    public NewOrderedItemDTO() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
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
