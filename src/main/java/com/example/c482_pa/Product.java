package com.example.c482_pa;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Product {


    //Creating Instance Variables for Class Product
    public final StringProperty name;
    public final IntegerProperty productID;
    public final IntegerProperty productInStock;
    public final DoubleProperty price;
    public final DoubleProperty min;
    public final DoubleProperty max;

    public Product() {

        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        productInStock = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();
        min = new SimpleDoubleProperty();
        max = new SimpleDoubleProperty();

    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public DoubleProperty productPriceProperty() {
        return price;
    }

    public StringProperty productNameProperty() {
        return name;
    }

    public DoubleProperty minPriceProperty() {
        return min;
    }

    public DoubleProperty maxPriceProperty() {
        return max;
    }

    public IntegerProperty productInventoryProperty() {
        return productInStock;
    }

    public int getProductID() {
        return productID.get();
    }

    public int getInStock() {
        return productInStock.get();
    }

    public String getProductName() {
        return name.get();
    }

    public double getMax() {
        return max.get();
    }

    public double getMin() {
        return min.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setProductID(int partID) {
        this.productID.set(partID);
    }

    public void setInStock(int inStock) {
        this.productInStock.set(inStock);
    }
    public ObservableList partsOfProduct(){
        return partsOfProduct();
    }
    public void setPrice(double price) {
        this.setPrice(price);
    }

    public void setMax(double max) {
        this.setMax(max);
    }

    public void setMin(double min) {
        this.setMin(min);
    }

    public static String validProduct(String name, int inv, int min, int max, double price, String error, ObservableList<Part> parts, String errorMessage) {
        double summingParts = 0.00;
        for (int s = 0; s < parts.size(); s++) {
            summingParts = summingParts + parts.get(s).getPrice();
        }
        if (name.equals("")) {
            errorMessage = errorMessage + ("Empty Name Field");
        }
        if (min < 0) {
            errorMessage = errorMessage + ("Inventory can not be 0");
        }
        if (min > max) {
            errorMessage = errorMessage + ("Min may not excede Max");
        }
        if (price < 0) {
            errorMessage = errorMessage + ("Price can not be less than $0");
        }
        if (inv < min || inv > max) {
            errorMessage = errorMessage + ("Inventory can not be outside the Min and Max");
        }
        if (parts.size() < 1) {
            errorMessage = errorMessage + ("Product must contain at least one part");
        }
        if (summingParts < price) {
            errorMessage = errorMessage + ("Price of Product must be more than parts cost");
        }
        return errorMessage;
    }
}

