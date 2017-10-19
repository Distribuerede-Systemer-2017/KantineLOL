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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static server.ServerImplDB.ImplDB.getConnection;

public class UserProvider {
    UserController userController = new UserController();


    public User logIn(String username, String password) {
        try {
            User user = new User();
            Digester digester = new Digester();

            PreparedStatement sql = getConnection().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
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


}
