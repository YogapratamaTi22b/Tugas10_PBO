public class InventoryItem {
    private String name;
    private double price;

    public InventoryItem(int id, String name, double price) {
        this.name = name;
        this.price = price;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}