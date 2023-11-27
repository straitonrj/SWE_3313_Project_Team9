package org.UCars;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Sales {
    private static int NumOfSales =0;
    private int SalesID;
    private int InventoryID;
    private int UserID;
    private double TotalCost;
    private static ArrayList<Sales> SalesList;

    Sales(int CustID, int InvID, double Price){
        NumOfSales++;
        SalesID=NumOfSales;
        UserID=CustID;
        InventoryID=InvID;
        TotalCost=Price;
    }
    //Constructor used when converting from Json
    Sales(int SaleID, int InvID, int CustID, double Price){
        SalesID=SaleID;
        InventoryID=InvID;
        UserID=CustID;
        TotalCost=Price;
    }

    public int getSalesID(){
        return SalesID;
    }
    public int getUserID(){
        return UserID;
    }
    public int getInventoryID(){
        return InventoryID;
    }
    public double getTotalCost(){
        return TotalCost;
    }
    public void AddToSalesList(Sales Sold){
        SalesList.add(Sold);
    }

    public JsonObject toJsonObject(){
        JsonObject TempObject = new JsonObject();
        TempObject.put("SalesID", SalesID);
        TempObject.put("UserID", UserID);
        TempObject.put("InventoryID", InventoryID);
        TempObject.put("TotalCost", TotalCost);
        return TempObject;
    }
    public static Sales fromJsonObject(JsonObject Temp){
        int SalesID = (int)Temp.get("SalesID");
        int UserID = (int)Temp.get("UserID");
        int InventoryID = (int)Temp.get("InventoryID");
        double TotalCost = (Double)Temp.get("TotalCost");
        return new Sales(SalesID,InventoryID,UserID,TotalCost);
    }
    private Path getDefaultPath(){
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("Sales.json");
    }
    void Save(){
        Save(getDefaultPath());
    }
    void Save(Path TempPath) {
        JsonArray TempArray = new JsonArray();
        for (int i = 0; i < SalesList.size(); i++) {
            Sales Temp = SalesList.get(i);
            TempArray.add(Temp.toJsonObject());
        }
        String JsonText = Jsoner.serialize(TempArray);
        try {
            Files.write(TempPath, JsonText.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
