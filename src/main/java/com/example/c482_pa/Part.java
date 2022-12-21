package com.example.c482_pa;

import javafx.beans.property.*;

public abstract class Part {

    //Creating Instance Variables for Class Part
   public final StringProperty name;
   public final IntegerProperty partID;
   public final IntegerProperty inStock;
   public final DoubleProperty price;
   public final IntegerProperty max;
   public final IntegerProperty min;

   public Part(){

       partID = new SimpleIntegerProperty();
       name = new SimpleStringProperty();
       inStock = new SimpleIntegerProperty();
       price = new SimpleDoubleProperty();
       min = new SimpleIntegerProperty();
       max = new SimpleIntegerProperty();

   }

    public IntegerProperty partIDProperty() {
        return partID;
    }
    public DoubleProperty partPriceProperty(){
       return price;
    }
    public StringProperty partNameProperty(){
       return name;
    }
    public IntegerProperty partMinProperty(){
       return min;
    }
    public IntegerProperty partMaxProperty(){
       return max;
    }
    public IntegerProperty partInventoryProperty(){
       return inStock;
    }

    public int getPartID() {
        return partID.get();
    }

    public int getInStock() {
        return inStock.get();
    }

    public String getPartName() {
        return name.get();
    }

    public int getMax() {
        return max.get();
    }

    public int getMin() {
        return min.get();
    }
    public double getPrice(){
       return price.get();
    }

    //public DoubleProperty priceProperty() {
      //  return price;
    //}
    public void setName(String name){
       this.name.set(name);
    }
    public void setPartID(int partID){
       this.partID.set(partID);
    }
    public void setInStock(int inStock){
       this.inStock.set(inStock);
    }
    public void setPrice(double price){
       this.setPrice(price);
    }
    public void setMax(Integer max){
       this.setMax(max);
    }
    public void setMin(Integer min){
       this.setMin(min);
    }
    public static String validPart(String name, int inv, int min, int max, double price, String error){
       if (inv < 1) {
           error = error + ("Inventory must contain at least one part.");
       }
       if (min > max){
           error = error + ("Min may not exceed max.");
       }
       if (inv > min || inv > max) {
           error = error + ("Inventory amount must be between minimum and maximum.");
       }
           if (name == null){
               error = error + ("Must give part a name.");
           }
           return error;
       }
    }

