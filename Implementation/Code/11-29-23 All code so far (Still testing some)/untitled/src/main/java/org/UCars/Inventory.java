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

public class Inventory {
    private static int NumOfInventory = 1;
    private int InventoryID;
    private String Name;
    private BigDecimal Price;
    private String Description;
    private String ImageURL;
    private static ArrayList<Inventory> InventoryList = new ArrayList<>();

    Inventory(String n, BigDecimal p, String d, String i){
        Name = n;
        Price = p;
        Description = d;
        InventoryID = NumOfInventory;
        ImageURL=i;
        NumOfInventory++;
    }
    Inventory(String n,BigDecimal p, String d, int ID, String Image){
        Name=n;
        Price=p;
        Description=d;
        InventoryID=ID;
        ImageURL=Image;
    }
    public void AddInventory(Inventory TempInventory){
        Inventory.Load();
        InventoryList.add(TempInventory);
        Inventory.Save();
    }

    public String getImageURL(){
        return ImageURL;
    }
    public static ArrayList<Inventory> getInventoryList(){
        return InventoryList;
    }
    public BigDecimal getPrice(){
        return Price;
    }
    public void setPrice(BigDecimal p){
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

    //The following techniques were inspired by a json.simple youtube video.
    //https://www.youtube.com/watch?v=ywLKpHw1MjQ Link to the Amazing Video by Mr Cressey
    public JsonObject toJsonObject(){
        JsonObject TempObject = new JsonObject();
        TempObject.put("InventoryID", InventoryID);
        TempObject.put("Name", Name);
        TempObject.put("Price", Price);
        TempObject.put("Description", Description);
        TempObject.put("ImageURL", ImageURL);
        return TempObject;
    }
    public static Inventory fromJsonObject(JsonObject Temp){
        String Name = (String)Temp.get("Name");
        String Description = (String)Temp.get("Description");
        BigDecimal Price = ((BigDecimal)Temp.get("Price"));
        int InvID = ((BigDecimal)Temp.get("InventoryID")).intValue();
        String ImageURL = (String)Temp.get("ImageURL");
        return new Inventory(Name, Price, Description,InvID,ImageURL);
    }
    private static Path getDefaultPath(){
        //String home = System.getProperty("user.home");
        //return Paths.get(home).resolve("Inventory.json");
        return Path.of("src/main/java/org/UCars/Inventory.json");
    }
    static void Save(){
        Save(getDefaultPath());
    }
    static void Save(Path TempPath) {
        JsonArray TempArray = new JsonArray();
        for (int i = 0; i < InventoryList.size(); i++) {
            Inventory TempInv = InventoryList.get(i);
            TempArray.add(TempInv.toJsonObject());
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
            Inventory TempInv = Inventory.fromJsonObject(TempObject);
            InventoryList.add(TempInv);
        }

    }
}
