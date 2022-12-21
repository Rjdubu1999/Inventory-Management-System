package com.example.c482_pa;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class inHouse extends Part {
    private final IntegerProperty machineID;
    public inHouse() {
        super();
        machineID = new SimpleIntegerProperty();

    }
    public void setPartMachineID(int machineID){
        this.machineID.set(machineID);
    }
public int getPartMachineId(){
      return this.machineID.get();
}
    }




