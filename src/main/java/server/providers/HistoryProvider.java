package server.providers;

import server.models.Item;
import server.models.Order;
import server.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static server.ServerImplDB.ImplDB.getConnection;

public class HistoryProvider {

    public ResultSet getOrders(String tablename) throws Exception {

        PreparedStatement sql = getConnection().prepareStatement("SELECT * FROM lol.order where user_id = 1");


        System.out.println(sql.executeQuery());
        return sql.executeQuery();


    }

    public ArrayList<Order> getOrders() {



    ArrayList<Order> orders = new ArrayList<>();

        try {
            ResultSet results = getOrders("order");
            System.out.println("results!: " + results);

            while (results.next()) {
                Order order = new Order(
                        results.getInt("id"),
                        results.getString("date"),
                        results.getInt("user_id")

                );

                orders.add(order);





            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return orders;


    }


}
