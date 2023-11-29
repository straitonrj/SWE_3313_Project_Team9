package org.UCars;

import java.math.BigDecimal;

public class Shipping {
    private int ShippingID;
    private String ShippingSpeed;
    private BigDecimal Price;

    Shipping(int ID, String Speed, BigDecimal Cost){
        ShippingID=ID;
        ShippingSpeed=Speed;
        Price=Cost;
    }
    public int getShippingID(){
        return ShippingID;
    }
    public String getShippingSpeed(){
        return ShippingSpeed;
    }

    public BigDecimal getPrice() {
        return Price;
    }
}
