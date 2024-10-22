package Entity;

public class Product {
    private final String productBrandName;
    private final double productPrice;
    private final int productStock;
    private int productId;

    public Product(int productId, String productBrandName, double productPrice, int productStock) {
        this.productBrandName = productBrandName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductQty() {
        return productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

}
