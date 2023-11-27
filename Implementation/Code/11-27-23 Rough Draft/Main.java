package org.UCars;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;

public class Main {
    public static void SignUp(String u, String p){
        User.Load();
        ArrayList<User> TempArray = User.getListOfUsers();
        boolean UsernameTaken=false;
        for(int i=0;i<TempArray.size();i++){
            User Temp = TempArray.get(i);
            if(u.equals(Temp.getUsername())){
                UsernameTaken=true;
            }
        }

        if(p.length() >=6 && UsernameTaken==false){
            User Temp = new User(u, p);
            Temp.AddUser(Temp);
            User.Save();
        }
        else{
            System.out.println("Password must contain a minimum of six characters.");
            //SignUp(u,p); Recursion or UI takes care of it?
            //Return User
        }
    }

    public static User Login(String u, String p){
        User.Load();
        User LoginTemp = null;
        ArrayList<User> ArrayTemp = User.getListOfUsers();
        for(int i=0;i <ArrayTemp.size(); i++){
            User Temp = ArrayTemp.get(i);
            if(Temp.getUsername().equals(u)){
                LoginTemp = Temp;
                if(LoginTemp.getPassword().equals(p)) {
                    return LoginTemp;
                }
            }
        }
        return null;
    }

    public static void AddInventory(User TempUser, String t, String d, Double p){
        Inventory TempInventory = new Inventory(t, p, d);
        TempInventory.AddInventory(TempInventory);
        Inventory.Save();
    }

    public static ArrayList<Inventory> CheckAvailableInventory(){
        Sales.Load();
        Inventory.Load();
        ArrayList<Sales> TempSale = Sales.getSalesList();
        ArrayList<Inventory> AvailableInventory = Inventory.getInventoryList();
        //Checks to see if InventoryID appears in the Sales database and removes it from available inventory if so
        for(int i=0; i< AvailableInventory.size(); i++){
            for(int j=0;j<TempSale.size();j++){
                if(TempSale.get(j).getInventoryID() == AvailableInventory.get(i).getInventoryID()){
                    AvailableInventory.remove(i);
                }
            }
        }
        return AvailableInventory;
    }

    //Basic bubble sort (Should be fine for demo but too slow for irl practice)
    public static ArrayList<Inventory> SortAvailableInventory(){
        ArrayList<Inventory> Temp = CheckAvailableInventory();
        for(int i=0;i< Temp.size();i++){
            if(i+1== Temp.size()){
                return Temp;
            }
            else if(Temp.get(i).getPrice() > Temp.get(i+1).getPrice()){
                Inventory TempInv = Temp.get(i);
                Temp.set(i,Temp.get(i+1));
                Temp.set(i+1,TempInv);
            }
        }
        return Temp;
    }

    public static void AddToCart(ArrayList<Inventory> Cart, Inventory temp){
        Cart.add(temp);
    }
    public static void RemoveFromCart(ArrayList<Inventory> Cart, Inventory temp){
        Cart.remove(temp);
    }

    public static Boolean ValidatePayment(String CardNum, int CSV,int EXPMonth,int EXPYear){
        if(CardNum.length()==16 && (CSV>0 && CSV<100) && (EXPMonth>0 && EXPMonth<13) && (EXPYear>2022 && EXPYear<2030)){
            return true;
        }
        return false;
    }

    public static double ApplySalesTax(ArrayList<Inventory> Cart){
        double Total=0;
        for(int i=0;i<Cart.size();i++){
            Inventory TempInv = Cart.get(i);
            Total+= TempInv.getPrice();
        }
        Total= (Total*.06) + Total;
        //Researching a good way to round a double to the hundredths place without losing value
        return Total;
    }
    public static void Checkout(ArrayList<Inventory> Cart, User CurrentUser){
        int UserID = CurrentUser.getUserID();
        for(int i=0;i<Cart.size();i++){
            Inventory TempInv = Cart.get(i);
            Sales Temp = new Sales(UserID, TempInv.getInventoryID(), TempInv.getPrice());
            Temp.AddToSalesList(Temp);
        }
    }

    public static void main(String[] args) {
        ArrayList<Inventory> Cart = new ArrayList<>();
       ArrayList<Inventory> Test = SortAvailableInventory();
       for(int i=0;i<Test.size();i++){
           Inventory temp = Test.get(i);
           System.out.println(temp.getName()+"\n"+temp.getDescription()+"\n"+temp.getPrice()+"\n");
       }

    }
}