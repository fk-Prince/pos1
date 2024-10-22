package Repository;

import Entity.ProductEntity;

import java.io.*;
import java.util.ArrayList;

public class ProductListService {

    private final File productFile = new File("src/main/resources/PRODUCT_LIST.txt");

    public ProductListService() {

    }

    public ArrayList<ProductEntity> getProductList() {
        ArrayList<ProductEntity> listOfProduct = new ArrayList<>();
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

                ProductEntity item = new ProductEntity(productId, productItem, productPrice, productQty);
                listOfProduct.add(item);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfProduct;
    }

    public ProductEntity getProductList(String itemName) {

        try {
            if (!productFile.exists()) productFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(productFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                if (itemName.equalsIgnoreCase(lines[0])) {
                    return new ProductEntity(Integer.parseInt(lines[0]),lines[1], Double.parseDouble(lines[2]), Integer.parseInt(lines[3]));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
