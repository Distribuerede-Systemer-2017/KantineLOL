package server.controller;

import server.models.User;
import server.utility.Digester;
import server.providers.UserProvider;
import java.util.ArrayList;



public class UserController {


    private Digester digester = new Digester();
    private UserProvider userProvider = new UserProvider();

    public boolean addUser(User user) {
        String hashedPassword = digester.hashWithSalt(user.getPassword());
        user.setPassword(hashedPassword);
        boolean result = userProvider.createUser(user);
        return result;
    }
}
