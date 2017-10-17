package server.ServerImplDB;

import server.models.Product;
import server.models.Order;


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

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM lol.product");

        //sql.setString(1, tablename);
        System.out.println(sql.executeQuery());
        return sql.executeQuery();
    }

    public ArrayList<Product> getProducts() {

        ArrayList<Product> products = new ArrayList<>();

        try {
            ResultSet results = getRecords("Product");
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
}
