package com.example.c482_pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private static ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;

    public static ObservableList<Part> getPartInventory(){
        return partInventory;

        }
        public static ObservableList<Product> getProductInventory(){
        return productInventory;
        }
        public static void addAPart(Part part){
        partInventory.add(part);
        }
        public static void deleteAPart(Part part){
        partInventory.remove(part);
        }
        public static void updateAPart(int index, Part part){
        partInventory.set(index, part);
        }
        public static int setPartIDCount(){
        partIDCount++;
        return partIDCount;
    }
    public static boolean partDeletion(Part part){
        boolean partAddBool = false;
        for(int partBool = 0; partBool< productInventory.size();partBool++){
            if (productInventory.get(partBool).partsOfProduct().contains(part)){
                partAddBool = true;
            }
        }
        return partAddBool;
    }
    public static boolean isInteger (String input){
        try{
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static int lookUpAPart(String term) {
        boolean partAddBool = false;
        int index = 0;
        if (isInteger(term)) {
            for (int i = 0; i < partInventory.size(); i++) {
                if (Integer.parseInt(term) == partInventory.get(i).getPartID()) {
                    index = i;
                    partAddBool = true;
                }
            }

        }
            else {
            for (int i = 0; i < partInventory.size(); i++) {
                if (term.equals(partInventory.get(i).getPartName())) {
                    index = i;
                    partAddBool = true;
                }
            }
        }
                    if(partAddBool = true) {
                        return index;

                    } else{
                        System.out.println("Part not found.");
                        return -1;
                    }
                }

                public static void addProduct(Product product){
        productInventory.add(product);
                }
                public  static void removeProduct(Product product){
        productInventory.remove(product);
                }
                public static void updateProduct(int index, Product product){
        productInventory.set(index, product);
                }
                public static int getProductIDCount(){
        productIDCount++;
        return productIDCount;
                }
                public static int lookProductUp( String term){
        boolean addProductBool = false;
        int index = 0;

        if (isInteger(term)){
            for (int i = 0; i <productInventory.size(); i++){
                if (Integer.parseInt(term) == productInventory.get(i).getProductID()){
                    index = i;
                    addProductBool = true;
                }
            }
        }
        else {
            for(int i = 0; i < productInventory.size(); i++){
                if(term.equals(productInventory.get(i).getProductName())){
                    index = i;
                    addProductBool = true;
                }
            }
        }
        if (addProductBool =true){
            return index;
        }
        else {
            System.out.println("Product not found.");
            return -1;
        }
                }

        public static void addAProduct(Product product){
        productInventory.add(product);
        }
    }

