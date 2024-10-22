package Repository.ProductRepository;

import Entity.Product;

import java.util.List;

public interface IProductList {
    List<Product> getProductList();
    Product getProductList(int productId);
}
