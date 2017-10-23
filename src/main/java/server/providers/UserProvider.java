package server.providers;

import com.google.gson.Gson;
import server.models.Order;
import server.models.User;
import server.utility.Digester;
import server.controller.UserController;
import server.utility.Token;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.ws.Response;
import server.ServerImplDB.ImplDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import static server.ServerImplDB.ImplDB.getConnection;

public class UserProvider {


    private Connection connection;


    public boolean createUser(User user) throws IllegalArgumentException {
        try {
            connection = getConnection();
            PreparedStatement createUser = connection
                    .prepareStatement("INSERT INTO lol.users (username, password, token) VALUES (?,?,?)");

            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());
            createUser.setString(3, user.getToken());

            createUser.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authorizeUser (String username, String password) {
        try {
            User user = new User();
            Digester digester = new Digester();
            connection = getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM lol.users WHERE username = ? AND password = ?");
            sql.setString(1, username);
            sql.setString(2, digester.hashWithSalt(password));

            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            System.out.println(user.getUsername() + user.getPassword());
            return user;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    //et sql statement der gemmer en token
    public User getToken (String token) throws Exception {
        try{
            User user = new User();
            connection = getConnection();
            PreparedStatement sql = connection.prepareStatement("INSERT INTO users where id = ?)");
            sql.setString(1, token);

            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()){
                user.setToken(resultSet.getString("token"));
            }
            System.out.println(user.getUsername() + user.getPassword() + user.getToken());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //et sql statement der sletter token
    public boolean deleteToken (int id) throws Exception {
        try {
            User user = new User();
            connection = getConnection();
            PreparedStatement sql = connection.prepareStatement("UPDATE users SET token = '-' WHERE id = ?");
            sql.setInt(1,id);

            sql.executeUpdate();

            user.setToken("-");

            System.out.println("Logged out");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createOrder(Order order) throws IllegalArgumentException {

        try {
            connection = getConnection();

            PreparedStatement createOrder = connection
                    .prepareStatement("INSERT INTO lol.order (date, user_id) VALUES (?,?)");

            createOrder.setString(1, order.getDate());
            createOrder.setInt(2, order.getUserId());

            createOrder.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserFromToken (String token) throws Exception {
        try {
            User user = new User();
            connection = getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM lol.users WHERE token = ?");
            sql.setString(1, token);

            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setToken(resultSet.getString("token"));

            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addProductToOrder(String products_id, int orderId) throws Exception {
        try {
            connection = getConnection();
            PreparedStatement PS = connection
                    .prepareStatement("INSERT INTO lol.items (products_id, order_id) VALUE (?, ?)");
            PS.setInt(1, Integer.parseInt(products_id));
            PS.setInt(2, orderId);

            PS.executeUpdate();
            return true;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}