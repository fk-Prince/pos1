package Main;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void displayProduct() {
        System.out.println("Product Id = " + id);
        System.out.println("Product Name = " + name);
        System.out.println("Product Price = " + price);
        System.out.println("Product Quantity = " + quantity);
        System.out.println("---------------------------");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
