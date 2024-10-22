package Repository.ProductRepository;

import Entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductListService implements IProductList{

    private final File productFile = new File("src/main/resources/PRODUCT_LIST.txt");


    public List<Product> getProductList() {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        try {
            if (!productFile.exists()) productFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(productFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int productId = Integer.parseInt(lines[0]);
                String productItem = lines[1];
                double productPrice = Double.parseDouble(lines[2]);
                int productQty = Integer.parseInt(lines[3]);

                Product item = new Product(productId, productItem, productPrice, productQty);
                listOfProduct.add(item);
            }


            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfProduct.stream()
                .sorted(Comparator.comparingInt(Product::getProductId))
                .toList();
    }

    public Product getProductList(int productId) {

        try {
            if (!productFile.exists()) productFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(productFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                if (productId == Integer.parseInt(lines[0])) {
                    return new Product(Integer.parseInt(lines[0]),lines[1], Double.parseDouble(lines[2]), Integer.parseInt(lines[3]));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
