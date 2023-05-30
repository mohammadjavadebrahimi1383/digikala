package org.example;
import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements Serializable {
    public static void save(Object obj, String fileName) throws IOException{
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Object load(String fileName) throws IOException, ClassNotFoundException{
        Object obj = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException{
        runMenu();
    }

    public static void runMenu() throws IOException, ClassNotFoundException{

        Scanner in = new Scanner(System.in);
        Shop digiKala = new Shop("digi kala","www.digikala.com","02133333333");

        digiKala = (Shop) load("save.txt");


        while (true) {
            System.out.print("1.User\n2.Admin\n3.Seller\n4.exit\n->");
            String order = in.nextLine();

            ///////////////////////////////////////////////////////user
            if (order.equals("1")) {
                System.out.print("1.sign in\n2.sign up\n->");
                order = in.nextLine();

                //sign in
                if (order.equals("1")){
                    System.out.print("Enter username\n->");
                    String username= in.nextLine();
                    if (!digiKala.doesUserExist(username)) System.out.println("This username doesn't exist");
                    else{
                        System.out.print("Enter password\n->");
                        String pass = in.nextLine();
                        if (digiKala.userPassChack(username, pass)){
                            //user menu
                            while (true) {
                                String email = digiKala.getUserEmail(username);
                                String phone = digiKala.getUserphone(username);
                                String address = digiKala.getUserAddress(username);
                                float wallet = digiKala.getUserWallet(username);
                                System.out.println("Username: "+username+", Email: "+email+", Phone: "+phone+", Address: "+address+", Wallet: "+wallet);
                                System.out.print("1.Money Requeste\n2.Edit your information\n3.List of product\n4.Shopping cart\n5.Order shopping cart to buy\n6.List of purchased products\n7.Add comment to product\n8.Log out\n->");
                                order = in.nextLine();

                                //money Requeste
                                if (order.equals("1")) {
                                    System.out.print("How much money do you want?\n->");
                                    float money = 0;
                                    try {
                                        money = in.nextFloat();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }
                                    in.nextLine();
                                    digiKala.RequestMoney(username, money);
                                    System.out.println("After the admin's approval, it will be credited to your account.");
                                }

                                //edit info
                                else if (order.equals("2")) {
                                    System.out.print("1.Edit password\n2.Edit Address\n3.Edit phone\n4.Edit email\n->");
                                    order = in.nextLine();
                                    System.out.print("Enter new\n->");
                                    String change = in.nextLine();

                                    switch(order) {
                                        case "1":
                                            digiKala.changeUserPass(username,change);
                                            break;
                                        case "2":
                                            digiKala.changeUseraddress(username,change);
                                            break;
                                        case "3":
                                            digiKala.changeUserphone(username,change);
                                            break;
                                        case "4":
                                            digiKala.changeUserEmail(username,change);
                                            break;
                                        default:
                                            System.out.println("Wrong order");
                                    }

                                }

                                //list of product
                                else if (order.equals("3")) {
                                    digiKala.printProduct();
                                }

                                //shopping cart
                                else if (order.equals("4")) {
                                    System.out.print("1.view shopping cart\n2.Update shopping cart\n3.Remove items\n->");
                                    order= in.nextLine();
                                    //view
                                    if (order.equals("1")){
                                        System.out.println(digiKala.getShoppingCart(username));
                                    }

                                    //update
                                    else if(order.equals("2")){
                                        System.out.print("Enter the name of the requested product\n->");
                                        String productname = in.nextLine();
                                        if(!digiKala.doesProductExist(productname)){
                                            System.out.println("We don't have this product.");
                                            continue;
                                        }

                                        System.out.print("How many "+productname+" do you want to have?\n->");
                                        int requrstedQuantity =0;
                                        try {
                                            requrstedQuantity = in.nextInt();
                                        }
                                        catch (InputMismatchException e){
                                            System.out.println("You should enter integer number");
                                        }
                                        in.nextLine();

                                        if(requrstedQuantity > digiKala.getQuantity(productname)){
                                            System.out.println("We don't have that many products.");
                                            continue;
                                        }
                                        Product a =digiKala.getProductByName(productname);
                                        Product foo = new Product(a.getName(),a.getCategory(),a.getSubcategory(),a.getCompany(),requrstedQuantity,a.getPrice(),a.getDetaile());
                                        digiKala.addToShoppingCart(username,foo);
                                        digiKala.decreaseQuanttiy(productname,requrstedQuantity);
                                    }

                                    //remove
                                    else if(order.equals("3")){

                                        System.out.print("Enter the name of the requested product\n->");
                                        String productname = in.nextLine();
                                        if(!digiKala.doesProductExist(productname)){
                                            System.out.println("We don't have this product.");
                                            continue;
                                        }
                                        System.out.print("How many "+productname+" do you want to remove?\n->");
                                        int requrstedQuantity =0;
                                        try {
                                            requrstedQuantity = in.nextInt();
                                        }
                                        catch (InputMismatchException e){
                                            System.out.println("You should enter integer number");
                                        }
                                        in.nextLine();

                                        int x = digiKala.getQuantityCart(username,productname);
                                        if(requrstedQuantity > x){
                                            System.out.println("You don't have that many products in your cart.");
                                            continue;
                                        }
                                        Product a =digiKala.getProductByName(productname);
                                        Product foo = new Product(a.getName(),a.getCategory(),a.getSubcategory(),a.getCompany(),x - requrstedQuantity,a.getPrice(),a.getDetaile());
                                        digiKala.removeShoppingCart(username,productname);
                                        digiKala.addToShoppingCart(username,foo);
                                        digiKala.increaseQuanttiy(productname,requrstedQuantity);

                                    }
                                    else System.out.println("Wrong order.");
                                }

                                //order
                                else if (order.equals("5")) {
                                    if (digiKala.sumOfPrice(username) > wallet){
                                        System.out.println("You don't have enough money.");
                                        continue;
                                    }
                                    System.out.println("After admin approval, the purchase is final.");
                                    digiKala.RequestOrdeer(username);
                                }

                                //purchased product
                                else if (order.equals("6")) {
                                    System.out.println(digiKala.getOrders(username));
                                }

                                //add comment
                                else if (order.equals("7")) {
                                    System.out.print("Enter name of product that you  want to comment.\n->");
                                    String prductName = in.nextLine();
                                    if (!digiKala.doesProductExist(prductName)) {
                                        System.out.println("This product does not exist");
                                    }
                                    else{
                                        System.out.print("Enter your comment\n->");
                                        String comment =username +": "+ in.nextLine();

                                        digiKala.addComment(prductName, comment);
                                    }
                                }

                                //log out
                                else if(order.equals("8")) break;

                                else System.out.println("Wrong order");
                            }
                            //////////////
                        }
                        else System.out.println("Password is wrong!");
                    }
                }

                //sign up
                else if (order.equals("2")){

                    System.out.print("Enter username\n->");
                    String username= in.nextLine();
                    if (digiKala.doesUserExist(username)) System.out.println("This username already have taken.");
                    else{
                        System.out.print("Enter password\n->");
                        String pass= in.nextLine();
                        System.out.print("Enter email\n->");
                        String email= in.nextLine();
                        System.out.print("Enter phone number\n->");
                        String phone= in.nextLine();
                        System.out.print("Enter address\n->");
                        String address= in.nextLine();
                        digiKala.addUser(username,pass,email, phone,address);
                    }

                }

                else {System.out.println("Wrong order.");}
            }

            //////////////////////////////////////////////////////////////Admin
            else if (order.equals("2")) {

                System.out.print("Enter username\n->");
                String username= in.nextLine();
                if (!digiKala.doesAdminExist(username)) System.out.println("This username doesn't exist");

                else{
                    System.out.print("Enter password\n->");
                    String pass = in.nextLine();
                    if (digiKala.AdminPassChack(username,pass)){
                        //admin menu
                        while (true){
                            String email1 = digiKala.getAdminEmail(username);
                            System.out.println("Username: "+username+", Email: "+email1);
                            System.out.print("1.Add admin\n2.List of users\n3.Money request\n4.Seller request\n5.Order request\n6.List of product\n7.List of orders\n8.Log out\n->");
                            order = in.nextLine();

                            //add admin
                            if(order.equals("1")){
                                System.out.print("Enter username\n->");
                                String newUsername= in.nextLine();
                                if (digiKala.doesAdminExist(newUsername)) System.out.println("This username already have taken.");
                                else {
                                    System.out.print("Enter password\n->");
                                    String newPass = in.nextLine();
                                    System.out.print("Enter email\n->");
                                    String email = in.nextLine();
                                    digiKala.addAdmin(newUsername, newPass, email);
                                }
                            }

                            //print users
                            else if(order.equals("2")) digiKala.printUsers();

                            //Money request
                            else if(order.equals("3")){

                                System.out.print("1.print request\n2.accept request\n->");
                                order = in.nextLine();

                                if (order.equals("1")) digiKala.printMonyRequesteds();

                                else if (order.equals("2")){
                                    System.out.print("Inter number of request that you want to accept\n->");
                                    int i =0;
                                    try {
                                        i = in.nextInt();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }
                                    in.nextLine();
                                    digiKala.depositMoneyRequestend(i);
                                }

                                else System.out.println("Wrong order");
                            }

                            //seler request
                            else if(order.equals("4")){
                                System.out.print("1.print request\n2.accept request\n->");
                                order = in.nextLine();

                                if (order.equals("1")) digiKala.printsellerRequest();

                                else if (order.equals("2")){
                                    System.out.print("Inter number of request that you want to accept\n->");
                                    int i =0;
                                    try {
                                        i = in.nextInt();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }
                                    in.nextLine();
                                    digiKala.confirmSeller(i);
                                }

                                else System.out.println("Wrong order");
                            }

                            //order request
                            else if(order.equals("5")){
                                System.out.print("1.print request\n2.accept request\n->");
                                order = in.nextLine();

                                if (order.equals("1")) digiKala.printOrderRequesteds();

                                else if (order.equals("2")){
                                    System.out.print("Inter number of request that you want to accept\n->");
                                    int i =0;
                                    try {
                                        i = in.nextInt();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }
                                    in.nextLine();
                                    digiKala.confirmOrder(i);
                                }

                                else System.out.println("Wrong order");
                            }


                            //list of product
                            else if(order.equals("6")){
                                digiKala.printProduct();
                            }

                            //list of order
                            else if(order.equals("7")){
                                digiKala.printOrders();
                            }

                            //log out
                            else if(order.equals("8")){
                                break;
                            }
                            else System.out.println("Wrong order");

                        }
                        ////////////
                    }
                    else System.out.println("Password is wrong!");
                }
            }

            /////////////////////////////////////////////////////////Seller
            else if (order.equals("3")) {
                System.out.print("1.sign in\n2.sign up\n->");
                order = in.nextLine();

                //sign in
                if (order.equals("1")){
                    System.out.print("Enter username\n->");
                    String username= in.nextLine();
                    if (!digiKala.doesSellerExist(username)) System.out.println("This username doesn't exist");
                    else{
                        System.out.print("Enter password\n->");
                        String pass = in.nextLine();
                        if (digiKala.SellerPassChack(username, pass)){

                            // seller menu
                            while (true) {
                                double wallet = digiKala.getSellerWallet(username);
                                System.out.println("Company: "+username+", Wallet: "+wallet);
                                System.out.print("1.Add product\n2.List of available product\n3.Log out\n->");
                                order = in.nextLine();

                                //add product
                                if (order.equals("1")) {
                                    System.out.print("Enter name of product\n->");
                                    String name = in.nextLine();
                                    System.out.print("Enter category of product\n->");
                                    String category = in.nextLine();
                                    System.out.print("Enter subcategory of product\n->");
                                    String subcategory = in.nextLine();

                                    System.out.print("Enter quantity of product\n->");
                                    int quantity=0;
                                    try {
                                        quantity = in.nextInt();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }

                                    System.out.print("Enter price of product\n->");
                                    float price = 0;
                                    try {
                                        price = in.nextFloat();
                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("You should enter number");
                                    }
                                    in.nextLine();

                                    HashMap<String,String> detaile = new HashMap<>();
                                    while (true){
                                        System.out.print("Do you want to add detaile for  product?(enter y or n)\n->");
                                        order = in.nextLine();

                                        if (order.equals("y") | order.equals("Y")){
                                            System.out.print("Enter Title\n->");
                                            String title = in.nextLine();
                                            System.out.print("Enter Description\n->");
                                            String Description = in.nextLine();
                                            detaile.put(title,Description);
                                        }
                                        else break;
                                    }
                                    Product product = new Product(name, category, subcategory,username, quantity, price, detaile);
                                    digiKala.addProduct(username, product);
                                }

                                //print Available product
                                else if(order.equals("2")){
                                    digiKala.printSellerProduct(username);
                                }

                                //log out
                                else if(order.equals("3")){
                                    break;
                                }

                                else System.out.println("Wrong order");
                            }

                            //////////////
                        }
                        else System.out.println("Password is wrong!");
                    }
                }

                //sign up
                else if (order.equals("2")){
                    System.out.print("Enter username\n->");
                    String company= in.nextLine();
                    if (digiKala.doesSellerExist(company)) System.out.println("This company already has requestend.");
                    else{
                        System.out.print("Enter password\n->");
                        String pass= in.nextLine();
                        digiKala.addSeller(company,pass);
                    }

                }

                else {System.out.println("Wrong order.");}
            }

            //exit
            else if (order.equals("4")) {
                save(digiKala, "save.txt");
                System.exit(0);
            }
            else System.out.println("wrong order!");
        }

    }
}