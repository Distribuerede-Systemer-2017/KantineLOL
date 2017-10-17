package server.models;

public class Item {

    private int id;
    private int productId;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Item(int id, int productId, int orderId) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
    }
}

