package com.example.BackendProject.models;

public class NewOrderDTO {

    private Long supermarketId;

    public NewOrderDTO(Long supermarketId) {
        this.supermarketId = supermarketId;
    }

    public NewOrderDTO() {
    }

    public Long getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(Long supermarketId) {
        this.supermarketId = supermarketId;
    }
}
