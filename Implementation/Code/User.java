public class User {
    private static int NumOfUsers=1;
    private static int UserID;
    private String Password;
    private String Username;
    private boolean IsAdmin;

    //Overloaded Constructor
    User(String u, String p){
        Username = u;
        Password = p;
        UserID = NumOfUsers;
        NumOfUsers++;
        IsAdmin = false;
    }

    // Getters and Setters
    String getUsername(){
        return Username;
    }
    void setUsername(String u){
        Username =u;
    }
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


}
