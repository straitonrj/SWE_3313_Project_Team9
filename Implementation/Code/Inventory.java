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
    
}
