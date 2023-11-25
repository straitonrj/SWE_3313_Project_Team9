package org.UCars;

public class Inventory {
    private static int NumOfInventory = 1;
    private int InventoryID;
    private String Name;
    private double Price;
    private String Description;

    Inventory(String n, double p, String d){
        Name = n;
        Price = p;
        Description = d;
        InventoryID = NumOfInventory;
        NumOfInventory++;
    }

    public double getPrice(){
        return Price;
    }
    public void setPrice(double p){
        Price =p;
    }
    public int getInventoryID(){
        return InventoryID;
    }
    public String getName(){
        return Name;
    }
    public String getDescription(){
        return Description;
    }
}
