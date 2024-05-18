public class Sale extends Inventory {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate date;
    private double revenue;

    public Sale(Drug drug, int quantity, LocalDate date2, double revenue) {
        this.id = drug.getId();
        this.name = drug.getName();
        this.price = drug.getPrice();
        this.quantity = quantity;
        this.date = date2;
        this.revenue = revenue;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}