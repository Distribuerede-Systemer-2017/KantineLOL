package server.models;

public class User {

    private int id;
    private String username;
    private int password;

    public User(int password, int id, String username) {
        this.password = password;
        this.id = id;
        this.username = username;

    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
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
