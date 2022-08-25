package com.example.clothingappframework.Model;

import java.util.ArrayList;

public class Cart {

    private String pid, pName,price,quantity,discount;
    private ArrayList<CartItem> cart;
    private int subTotal; //Total cart price before tax
    private int total; //Total cart price after tax



    public Cart() {
    }

    public Cart(String pid, String pName, String price, String quantity, String discount) {
        this.pid = pid;
        this.pName = pName;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


}
