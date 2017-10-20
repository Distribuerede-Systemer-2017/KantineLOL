package server.ServerImplDB;


import java.sql.*;

public class ImplDB {

    private static Connection connection;

    public static Connection getConnection() throws Exception {
        System.out.println("Get connection!");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("try");
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connection = DriverManager
                    .getConnection("jdbc:mysql://distribueredesystemer." +
                            "cqsg17giwvxa.eu-central-1.rds.amazonaws.com:3306/lol", "dis2017", "doekdis2017");

            //.getConnection("jdbc:mysql://localhost:3306/DIS_Project?useSSL=false&serverTimezone=GMT", "root", "hej");


            System.out.println("Connection - - -- - - - - -- - - -- !: " + connection);
            return connection;
        } catch (Exception e) {
            System.out.println("Ramt exception!");
            System.out.println(e);
        }
        return null;
    }

}






