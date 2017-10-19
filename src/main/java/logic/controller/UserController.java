import server.models.User;

package logic.controller;

public class UserController {


    public boolean addUser(User user) {
        String hashedPassword = dig.hashWithSalt(user.getPassword());
        user.setPassword(hashedPassword);
        boolean result = dbConnection.addUser(user);
        return result;
    }
}
