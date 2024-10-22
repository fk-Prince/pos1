package Main;

public class Payment extends Product {

    public Payment(int id, String name, double price, int quantity) {
        super(id, name, price, quantity);
    }

    public double compute() {
        double total = getPrice() * getQuantity();
        double TAX = 0.12;
        return (total * TAX) + total;
    }
}
