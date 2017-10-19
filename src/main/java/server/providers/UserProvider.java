package server.providers;

import com.google.gson.Gson;
import server.models.Order;
import server.models.User;
import server.utility.Digester;
import server.controller.UserController;

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
                    .prepareStatement("INSERT INTO lol.users (username, password) VALUES (?,?)");

            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());

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
            sql.setString(2, password);

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

    //en sql statement der gemmer en token
    /*public User saveToken ( String username, String password, int token) throws Exception {
        User user = new User();
        connection = getConnection();
        PreparedStatement sql = connection.prepareStatement("INSERT INTO users (username, password, token) VALUES (?)");
        sql.setString(1, username);
        sql.setString(2, password);
        sql.setString(3, token);

    }*/

    //en sql statement der sletter token


}