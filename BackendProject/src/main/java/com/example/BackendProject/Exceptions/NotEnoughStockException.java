package com.example.BackendProject.Exceptions;

public class NotEnoughStockException extends Exception{

    public NotEnoughStockException(String message){
        super(message);
    }
}
