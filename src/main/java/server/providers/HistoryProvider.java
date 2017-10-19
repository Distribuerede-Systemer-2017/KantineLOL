package server.providers;

import server.models.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static server.ServerImplDB.ImplDB.getConnection;

public class HistoryProvider {

    public ResultSet getHistorys(String tablename) throws Exception {


        PreparedStatement sql = getConnection().prepareStatement("SELECT * FROM lol.order WHERE user_id  = 1");


        System.out.println(sql.executeQuery());
        return sql.executeQuery();
    }

    public ArrayList<Order> getOrders() {

        ArrayList<Order> orders = new ArrayList<>();


        try {
            ResultSet results = getHistorys("Order");

            while (results.next()) {
                System.out.println("processing order!");

                Order order = new Order(
                        results.getInt("id"),
                        results.getString("date"),
                        results.getInt("user_id")
                );

                orders.add(order);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return orders;

    }
}
