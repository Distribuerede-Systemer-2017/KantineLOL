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

public class UserProvider {
    UserController userController = new UserController();


    private Connection connection;


    public boolean createUser(User user) throws IllegalArgumentException {
        try {

            PreparedStatement createUser = connection
                    .prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");

            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());

            int rowsAffected = createUser.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }


}