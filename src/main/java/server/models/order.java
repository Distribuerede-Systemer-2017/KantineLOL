package server.models;

public class order {


    private int id;
    private int userId;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public order(int id, String date, int userId) {
        this.id = id;
        this.date = date;
        this.userId = userId;
    }


}



