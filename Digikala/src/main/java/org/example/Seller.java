package org.example;
import java.io.Serializable;
import java.util.HashMap;

public class Seller implements Serializable {
    private String company, password;
    private HashMap<String,Product> availableProducts;
    private float wallet;

    public Seller(String company, String password) {
        this.company = company;
        this.password = password;
        this.availableProducts = new HashMap<>();
    }
    public boolean sellerPassCheck(String passInput){
        if (passInput.equals(password)) return true;
        return false;
    }

    public void addAvailableSellerProducts(Product products) {
        this.availableProducts.put(products.getName(),products);
    }
    public void printAvailableSellerProducts() {
        System.out.println(availableProducts);
    }

    public void setAvailableProducts(String productName, int i){
        availableProducts.get(productName).setQuantity(i);
    }
    public float getWallet() {
        return wallet;
    }

    public void addWallet(float wallet) {
        this.wallet += wallet;
    }
}
