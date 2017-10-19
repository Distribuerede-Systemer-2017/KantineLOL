package server.providers;

import server.models.User;
import server.ServerImplDB.ImplDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class UserProvider {

    private Connection connection;


    public boolean createUser(User user) throws IllegalArgumentException {
        try {

            PreparedStatement createUser = connection
                    .prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");

            createUser.setString(1, user.getUsername());
            createUser.setInt(2, user.getPassword());

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
