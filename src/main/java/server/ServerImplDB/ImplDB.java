package server.ServerImplDB;

import server.models.Product;

import java.sql.*;
import java.util.ArrayList;

public class ImplDB {

    private static Connection connection = null;

    private static Connection getConnection() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connection = DriverManager
                    .getConnection("jdbc:mysql://distribueredesystemer." +
                            "cqsg17giwvxa.eu-central-1.rds.amazonaws.com:3306/lol", "dis2017", "doekdis2017");

            return connection;
        } catch (Exception e) {

        }
        return null;
    }

    public ResultSet getRecords(String tablename) throws Exception {

        PreparedStatement sql = getConnection().prepareStatement("SELECT * FROM ?");
        sql.setString(1, tablename);
        return sql.executeQuery();
    }

    public ArrayList<Product> getProducts() {

        ArrayList<Product> products = new ArrayList<>();

        try {
            ResultSet results = getRecords("Product");

            while(results.next()){
                Product product = new Product(
                        results.getInt("type"),
                        results.getInt("id"),
                        results.getString("product name"),
                        results.getString("product price")
                );

                products.add(product);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
