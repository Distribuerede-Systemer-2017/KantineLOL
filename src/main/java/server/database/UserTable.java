package server.database;


import server.models.user;

import java.util.ArrayList;

public class UserTable {

    private ArrayList<user> users;
    private static UserTable instance = null;

    protected UserTable() {
        this.users = new ArrayList<>();

        user user1 = new user();
        user1.setId(1);
        user1.setAge(26);
        user1.setName("Jesper Bruun");

        this.users.add(user1);

        user user2 = new user();
        user2.setId(2);
        user2.setAge(24);
        user2.setName("Daniel Franch");

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
