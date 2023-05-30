package org.example;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Order implements Serializable {
    private float price;
    private String buyer,seller;
    private Date date;
    private HashMap<String,Product> SalesProduct;

    public Order(float price, String buyer, String seller, Date date, HashMap<String, Product> salesProduct) {
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.date = date;
        SalesProduct = salesProduct;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", buyer='" + buyer + '\'' +
                ", seller='" + seller + '\'' +
                ", date=" + date +
                ", SalesProduct=" + SalesProduct +
                '}';
    }
}
