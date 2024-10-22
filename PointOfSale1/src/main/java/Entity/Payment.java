package Entity;

public class Payment extends ProductEntity {

    public Payment(int productId, String productBrandName, double productPrice, int productStock) {
        super(productId, productBrandName, productPrice, productStock);
    }

    public double compute() {
        return getProductPrice() * getProductQty();
    }
}
