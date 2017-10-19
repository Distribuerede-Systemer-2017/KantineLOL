package server.models;

import server.utility.Digester;

public class User {

    private int id;
    private String username;
    private String password;
    private Digester digester = new Digester();

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = digester.hashWithSalt(password);
    }

    public User () {

    }

    public String getPassword() {
        return digester.hashWithSalt(password);
    }

    public void setPassword(String password) {
        this.password = digester.hashWithSalt(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
