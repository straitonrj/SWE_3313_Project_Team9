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


public class User {
    private static int NumOfUsers=1;
    private static int UserID;
    private String Password;
    private String Username;
    private boolean IsAdmin;
    private static ArrayList<User> ListOfUsers = new ArrayList<User>();

    //Overloaded Constructor
    User(String u, String p){
        Username = u;
        Password = p;
        UserID = NumOfUsers;
        NumOfUsers++;
        IsAdmin = false;
    }

    public static void AddUser(User TempUser){
        ListOfUsers.add(TempUser);
    }
    // Getters and Setters
    String getUsername(){
        return Username;
    }
    void setUsername(String u){
        Username =u;
    }
    /*void setUserID(int ID){
        UserID = ID;
    }
     */
    String getPassword(){
        return Password;
    }
    void setPassword(String p){
        Password =p;
    }
    int getUserID(){
        return UserID;
    }
    Boolean getIsAdmin(){
        return IsAdmin;
    }
    void setIsAdmin(Boolean b){
        IsAdmin = b;
    }
    //The following shit was found in a json.simple youtube video. I in no way take any credit
    //https://www.youtube.com/watch?v=ywLKpHw1MjQ Link to the Amazing Video by Mr Cressey
    public JsonObject toJsonObject(){
        JsonObject TempObject = new JsonObject();
        TempObject.put("UserID", UserID);
        TempObject.put("Username", Username);
        TempObject.put("Password", Password);
        TempObject.put("IsAdmin", IsAdmin);
        return TempObject;
    }
    public static User fromJsonObject(JsonObject Temp){
        String Username = (String)Temp.get("Username");
        String Password = (String)Temp.get("Password");
        int UserID = ((BigDecimal)Temp.get("UserID")).intValue();
        Boolean IsAdmin = (Boolean)Temp.get("IsAdmin");
        return new User(Username, Password);
    }


    private Path getDefaultPath(){
       String home = System.getProperty("user.home");
       return Paths.get(home).resolve("User.json");
    }
    void save(){
        save(getDefaultPath());
    }
    void save(Path TempPath){
        JsonArray TempArray = new JsonArray();
        for(int i=0;i<ListOfUsers.size();i++){
            User TempUser = ListOfUsers.get(i);
            TempArray.add(TempUser.toJsonObject());
        }
        String JsonText = Jsoner.serialize(TempArray);
        try{
            Files.write(TempPath,JsonText.getBytes(), StandardOpenOption.CREATE);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    void load(){
        load(getDefaultPath());
    }
    void load(Path TempPath){
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
            User TempUser = User.fromJsonObject(TempObject);
            ListOfUsers.add(TempUser);
        }

    }
}