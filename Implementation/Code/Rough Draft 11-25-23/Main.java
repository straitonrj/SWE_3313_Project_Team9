package org.UCars;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;

public class Main {
    public static void SignUp(String u, String p){
        //Check that password meets requirements & username isn't taken
        if(p.length() >=6){
            User temp = new User(u, p);
            User.AddUser(temp);
            temp.save();
        }
        //Add else if that will run if username is taken but password is valid
        else{
            System.out.println("Password must contain a minimum of six characters.");
            //SignUp(u,p); Recursion or UI takes care of it?
        }
    }

    public static void Login(String u, String p){
        //Parse JSON Array to regular Array
        User LoginTemp;
        User ArrayTemp[] = new User[10];
        for(int i=0;i <ArrayTemp.length; i++){
            User temp = ArrayTemp[i];
            if(temp.getUsername().equals(u)){
                LoginTemp = temp;
            }
            if(i==ArrayTemp.length && temp.getUsername()!= u){
                System.out.println("User not found");
                return;
            }
        }
        if(LoginTemp.getPassword().equals(p)){
            //Login successful? Switch to home screen?
        }
    }

    public static void AddInventory(User TempUser, String t, String d, Double p){
        Inventory TempInventory = new Inventory(t, p, d);
    }

    public static Inventory[] CheckAvailableInventory(Inventory[] temp){
        int ArrayLength = temp.length;
        Inventory[] AvailableInventory = new Inventory[temp.length];
        for(int i=0; i< ArrayLength; i++){
            //Check sales database for inventoryID number
        }
        return AvailableInventory;
    }

    //Basic bubble sort (Should be fine for demo but too slow for irl practice)
    public static Inventory[] SortAvailableInventory(Inventory[] Temp){
        for(int i=0;i< Temp.length;i++){
            if(Temp[i].getPrice() > Temp[i+1].getPrice()){
                Inventory TempInv = Temp[i];
                Temp[i] = Temp[i+1];
                Temp[i+1] = TempInv;
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

    public static void main(String[] args) {
        ArrayList<Inventory> Cart = new ArrayList<Inventory>();

    }
}