package org.fstbm.elivraison.data.model;

import java.io.Serializable;
import java.util.List;

public class Produit implements Serializable {

    private String name;
    private double price;
    private int quantity;


    public Produit() {
    }

    public Produit(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int qunatity) {
        this.quantity = qunatity;
    }



}
