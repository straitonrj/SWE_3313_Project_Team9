package org.UCars;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    private static User CurrentUser;

    public static void setCurrentUser(User Temp){
        CurrentUser = Temp;
    }
    public static User getCurrentUser(){
        return CurrentUser;
    }
    //Works
    public static void SignUp(String u, String p){
        User TempUser = new User();
        ArrayList<User> TempArray = TempUser.getListOfUsers();
        boolean UsernameTaken=false;
        for(int i=0;i<TempArray.size();i++){
            User Temp = TempArray.get(i);
            if(u.equals(Temp.getUsername())){
                UsernameTaken=true;
                System.out.println("Username Taken");
                return;
            }
        }

        if(p.length() >=6 && UsernameTaken==false){
            User Temp = new User(u, p);
            Temp.AddUser(Temp);
        }
        else{
            System.out.println("Password must contain a minimum of six characters.");
        }
    }

    //Works
    //Going through all users iteratively would be too slow irl
    public static boolean Login(String u, String p){
        User LoginTemp = new User();
        LoginTemp.Load();
        ArrayList<User> ArrayTemp = LoginTemp.getListOfUsers();
        for(int i=0;i <ArrayTemp.size(); i++){
            User Temp = ArrayTemp.get(i);
            if(Temp.getUsername().equals(u)){
                LoginTemp = Temp;
                if(LoginTemp.getPassword().equals(p)) {
                    setCurrentUser(LoginTemp);
                    return true;
                }
            }
        }
        return false;
    }
    public static void PromoteToAdmin(User FutureAdmin){
        if(CurrentUser.getIsAdmin()){
            FutureAdmin.setIsAdmin();
        }
    }

    public static void AddInventory(String t, String d, BigDecimal p, String image){
        Inventory TempInventory = new Inventory(t, p, d, image);
        TempInventory.AddInventory(TempInventory);
        TempInventory.Save();
    }

    //Works
    public static ArrayList<Inventory> CheckAvailableInventory(){
        Sales.Load();
        Inventory TempInv = new Inventory();
        TempInv.Load();
        Sales Temp = new Sales();
        ArrayList<Sales> TempSale = Temp.getSalesList();
        ArrayList<Inventory> AvailableInventory = TempInv.getInventoryList();
        //Checks to see if InventoryID appears in the Sales database and removes it from available inventory if so
        try {
            for (int i = 0; i < AvailableInventory.size(); i++) {
                for (int j = 0; j < TempSale.size(); j++) {
                    if (TempSale.get(j).getInventoryID() == AvailableInventory.get(i).getInventoryID()) {
                        AvailableInventory.remove(i);
                    }
                }
            }
            return AvailableInventory;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("No Available Inventory");
            return null;
        }
    }

    //Works
    //Basic bubble sort (Should be fine for demo but too slow for irl practice)
    public static ArrayList<Inventory> SortAvailableInventory(){
        try {
            ArrayList<Inventory> Temp = CheckAvailableInventory();
            for (int i = 0; i < Temp.size(); i++) {
                if (i + 1 == Temp.size()) {
                    return Temp;
                } else if (Temp.get(i).getPrice().doubleValue() > Temp.get(i + 1).getPrice().doubleValue()) {
                    Inventory TempInv = Temp.get(i);
                    Temp.set(i, Temp.get(i + 1));
                    Temp.set(i + 1, TempInv);
                }
            }
            return Temp;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("No Available Inventory to sort");
            return null;
        }
    }
    //Works
    public static ArrayList<Inventory> SearchInventory(String Search){
        ArrayList<Inventory> AvailableInv = CheckAvailableInventory();
        ArrayList<Inventory> MatchingInv = new ArrayList<>();
        for(int i=0;i<AvailableInv.size();i++){
            String TempName = AvailableInv.get(i).getName();
            if(TempName.contains(Search)){
                MatchingInv.add(AvailableInv.get(i));
            }
        }
        return MatchingInv;
    }

    public static void AddToCart(ArrayList<Inventory> Cart, Inventory temp){
        Cart.add(temp);
    }
    public static void RemoveFromCart(ArrayList<Inventory> Cart, Inventory temp){
        Cart.remove(temp);
    }

    //Works
    public static Boolean ValidatePayment(String CardNum, int CSV,int EXPMonth,int EXPYear){
        if(CardNum.length()==16 && (CSV>0 && CSV<1000) && (EXPMonth>0 && EXPMonth<13) && (EXPYear>2022 && EXPYear<2030)){
            return true;
        }
        return false;
    }

    public static BigDecimal ApplySalesTax(ArrayList<Inventory> Cart){
        BigDecimal Total= new BigDecimal(0.00);
        for(int i=0;i<Cart.size();i++){
            Inventory TempInv = Cart.get(i);
            Total= Total.add(TempInv.getPrice());
        }
        BigDecimal Tax = new BigDecimal(.06);
        Total= (Total.multiply(Tax).add(Total));
        BigDecimal Hundred = new BigDecimal(100);
        Total= Total.setScale(2, RoundingMode.CEILING);
        return Total;
    }

    public static void Checkout(ArrayList<Inventory> Cart, int ShippingChoice){
        int UserID = CurrentUser.getUserID();
        for(int i=0;i<Cart.size();i++){
            Inventory TempInv = Cart.get(i);
            Sales Temp = new Sales(UserID, TempInv.getInventoryID(), ShippingChoice, TempInv.getPrice());
            Temp.AddToSalesList(Temp);
        }
    }

    public static void main(String[] args) {
        LoginScreen StartUp = new LoginScreen();
        StartUp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ArrayList<Inventory> Cart = new ArrayList<>();
        Shipping[] ShippingOptions = new Shipping[3];
        BigDecimal Zero = new BigDecimal(0.00);
        BigDecimal Nineteen = new BigDecimal(19.00);
        BigDecimal TwentyNine = new BigDecimal(29.00);
        ShippingOptions[0] = new Shipping(1, "Standard Shipping", Zero);
        ShippingOptions[1] = new Shipping(2, "3-Day Shipping", Nineteen);
        ShippingOptions[2] = new Shipping(3, "Overnight Shipping", TwentyNine);

        /*Cart = CheckAvailableInventory();
        Inventory[] Temp = Cart.toArray(new Inventory[0]);
        System.out.println(Temp.length);
        for(int i=0;i<Temp.length; i++){
            System.out.println(Temp[i].getDescription());
        }*/
        CheckAvailableInventory();

    }
}