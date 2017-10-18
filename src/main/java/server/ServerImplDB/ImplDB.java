package server.ServerImplDB;

import server.models.Product;
import server.models.User;
import server.models.Order;


import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;

public class ImplDB {

    private static Connection connection;

    private static Connection getConnection() throws Exception {
        System.out.println("Get connection!");
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("try");
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connection = DriverManager
                    .getConnection("jdbc:mysql://distribueredesystemer." +
                            "cqsg17giwvxa.eu-central-1.rds.amazonaws.com:3306/lol", "dis2017", "doekdis2017");
            System.out.println("Connection - - -- - - - - -- - - -- !: " + connection);
            return connection;
        } catch (Exception e) {
            System.out.println("Ramt exception!");
            System.out.println(e);
        }
        return null;
    }

    public ResultSet getRecords(String tablename) throws Exception {

        connection = getConnection();

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM "+tablename);

        System.out.println(sql.executeQuery());
        return sql.executeQuery();
    }

    public ArrayList<Product> getProducts() {

        ArrayList<Product> products = new ArrayList<>();

        try {
            ResultSet results = getRecords("product");
            System.out.println("results!: " + results);

            while (results.next()) {
                Product product = new Product(
                        results.getInt("type"),
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("price")
                );

                products.add(product);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {
            ResultSet results = getRecords("user");

            while (results.next()) {

                User user = new User(
                        results.getInt("id"),
                        results.getString("username"),
                        results.getString("password")
                );

                users.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}

    /*public ArrayList<Order> getorders() {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            ResultSet results = getRecords("Order");

                    while(results.next()) {
                Order order = new Order(
                        results.getInt("t")

                );

                orders.add(order);
                    }
                    }
                    catch (Exception e) {     
                    e.printStackTrace();




    }                                    */