package com.example.BackendProject.models;

public enum OrderStatus {

    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    OUT_FOR_DELIVERY("Out for delivery"),
    COMPLETED("Completed");

    final String displayStatus;

    OrderStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

}
