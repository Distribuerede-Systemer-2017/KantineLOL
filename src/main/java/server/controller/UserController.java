package server.controller;

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import server.models.User;
import server.utility.Digester;
import server.providers.UserProvider;



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
