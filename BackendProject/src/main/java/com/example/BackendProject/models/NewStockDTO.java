package com.example.BackendProject.models;

public class NewStockDTO {
    private long id;
    private int quantity;
    private String expiryDate;
    private long itemId;

    public NewStockDTO(int quantity, String expiryDate, long itemId) {
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.itemId = itemId;
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

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
