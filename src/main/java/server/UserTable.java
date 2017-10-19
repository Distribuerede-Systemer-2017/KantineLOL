package server;

        import server.models.User;

<<<<<<< HEAD:src/main/java/server/UserTable.java
        import java.util.ArrayList;
=======
import server.models.user;

import java.util.ArrayList;
>>>>>>> master:src/main/java/server/database/UserTable.java

public class UserTable {

    private ArrayList<user> users;
    private static UserTable instance = null;

    protected UserTable() {
        this.users = new ArrayList<>();

        user user1 = new user();
        user1.setId(1);
        user1.setPassword(1234);
        user1.setUsername("Jesper Bruun");

        this.users.add(user1);

        user user2 = new user();
        user2.setId(2);
        user2.setPassword(24);
        user2.setUsername("Daniel Franch");

        this.users.add(user2);

    }

    public static UserTable getInstance() {
        if (instance == null) {
            instance = new UserTable();
        }
        return instance;
    }

    public void addUser(user user) {
        this.users.add(user);
    }

    public ArrayList<user> getUsers() {
        return this.users;
    }

    public user findById(int id) {
        for (server.models.user user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
