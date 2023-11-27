package org.UCars;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.IOException;
import java.math.BigDecimal;
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
    private static ArrayList<Sales> SalesList = new ArrayList<>();

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
    public static ArrayList<Sales> getSalesList(){
        return SalesList;
    }
    public void AddToSalesList(Sales Sold){
        SalesList.add(Sold);
        Sales.Save();
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
        int SalesID = ((BigDecimal)Temp.get("SalesID")).intValue();
        int UserID = ((BigDecimal)Temp.get("UserID")).intValue();
        int InventoryID = ((BigDecimal)Temp.get("InventoryID")).intValue();
        double TotalCost = ((BigDecimal)Temp.get("TotalCost")).doubleValue();
        return new Sales(SalesID,InventoryID,UserID,TotalCost);
    }
    private static Path getDefaultPath(){
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("Sales.json");
    }
    static void Save(){
        Save(getDefaultPath());
    }
    static void Save(Path TempPath) {
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

    static void Load(){
        Load(getDefaultPath());
    }
    static void Load(Path TempPath){
        String JSONText;
        JsonArray TempArray;
        try {
            JSONText = new String(Files.readAllBytes(TempPath));
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            TempArray = (JsonArray)Jsoner.deserialize(JSONText);
        }
        catch(JsonException e){
            throw new RuntimeException(e);
        }
        for(int i=0;i<TempArray.size();i++){
            JsonObject TempObject = (JsonObject)TempArray.get(i);
            Sales Temp = Sales.fromJsonObject(TempObject);
            SalesList.add(Temp);
        }

    }
}
