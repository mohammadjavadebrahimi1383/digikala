package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {
    private String username, password, email, phone, address;
    private float wallet;
    private HashMap<String,Product> shoppingCart;
    private ArrayList <Order> orders;
    private float moneyRequested;

    public User(String username, String password, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.shoppingCart = new HashMap<>();
        this.orders = new ArrayList<>();
        this.wallet=0;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public float getMoneyRequested() {
        return moneyRequested;
    }

    public void setMoneyRequested(float moneyRequested) {
        this.moneyRequested = moneyRequested;
    }

    public void addWallet(float wallet) {
        this.wallet += wallet;
    }

    public boolean userPassCheck(String passInput){
        if (passInput.equals(password)) return true;
        return false;
    }

    public int getQ(String productName){
        return shoppingCart.get(productName).getQuantity();
    }
    public HashMap<String,Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<String, Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addShoppingCart(Product product) {
        this.shoppingCart.put(product.getName(),product);
    }
    public void removeFromCart(String productname){
        this.shoppingCart.remove(productname);
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", wallet=" + wallet +
                ", shoppingCart=" + shoppingCart +
                ", orders=" + orders +
                ", moneyRequested=" + moneyRequested +
                '}';
    }
}
