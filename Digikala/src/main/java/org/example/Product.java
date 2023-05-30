package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Product extends SubCategory implements Serializable {
    private String name, category, subcategory;
    private String company;
    private int quantity;
    private float price;
    private HashMap <String,String> detaile;
    private ArrayList <String> comments;

    public Product(String name, String category, String subcategory,String company, int quantity, float price, HashMap<String, String> detaile) {
        super(category, subcategory);
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.company = company;
        this.quantity = quantity;
        this.price = price;
        this.detaile = detaile;
        this.comments= new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public float getPrice() {
        return price;
    }

    public HashMap<String, String> getDetaile() {
        return detaile;
    }

    public void addComment(String comment){ comments.add(comment);}

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", company='" + company + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", detaile=" + detaile +
                ", comments=" + comments +
                '}';
    }
}
