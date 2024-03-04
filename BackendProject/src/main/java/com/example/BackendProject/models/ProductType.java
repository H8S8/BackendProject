package com.example.BackendProject.models;

public enum ProductType {

    DAIRY("Dairy"),
    BAKERY("Bakery"),
    FRUITANDVEG("Fruit and Veg"),
    MEAT("Meat"),
    FROZEN("Frozen");

    final String displayName;

    ProductType(String displayName){
        this.displayName = displayName;
    }

    public static ProductType valueOfDisplayName(String aisleName){
        for (ProductType productType : ProductType.values()){
            if (productType.displayName.equals(aisleName)){
                return productType;
            }
        }
        return null;
    }
}
