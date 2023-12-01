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
   // private static int  NumOfUsers = ;
    private int UserID;
    private String Password;
    private String Username;
    //private String Email;
    private boolean IsAdmin;
    private ArrayList<User> ListOfUsers = new ArrayList<User>();

    User(){

    }
    //Overloaded Constructor
    User(String u, String p){
        Load();
        Username = u;
        Password = p;
        UserID = getListOfUsers().size();
        System.out.println(getListOfUsers().size());
        IsAdmin = false;
    }
    //Constructor used when converting json to an existing user
    User(String u, String p, int ID, boolean Admin){
        Username=u;
        Password=p;
        UserID=ID;
        IsAdmin=Admin;
    }
    public void AddUser(User TempUser){
        TempUser.Load();
        ListOfUsers.add(TempUser);
        TempUser.Save();
    }
    // Getters and Setters
    public ArrayList<User> getListOfUsers(){
        //System.out.println("List of Users:" + ListOfUsers);
        return ListOfUsers;
    }
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
    void setIsAdmin(){
        IsAdmin = true;
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
        return new User(Username, Password, UserID, IsAdmin);
    }

    private static Path getDefaultPath(){
       //String home = System.getProperty("src/main/java/org/UCars/User.json");
       //return Paths.get(home).resolve("User.json");
        return Path.of("untitled/src/main/java/org/UCars/User.json");
    }

     void Save(){
        Save(getDefaultPath());
    }

     void Save(Path TempPath){
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

      void Load(){
        Load(getDefaultPath());
    }

     void Load(Path TempPath){
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