package server.ServerImplDB;

import java.sql.*;

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

    //public ResultSet getProduct(String product) throws Exception {

        //preparedStatement sql = getConnection().prepareStatement("SELECT * FROM product ");
        //sql.setString(1, getProduct(Salat));
        //return sql.executeQuery();
    }
//}
