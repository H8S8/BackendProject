package com.example.BackendProject.models;

public class NewStockDTO {
    private long id;
    private int quantity;
    private String expiryDate;
    private long itemId;

    private int unitPrice;

    public NewStockDTO(int quantity, String expiryDate, long itemId, int unitPrice) {
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
    }

    public NewStockDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
