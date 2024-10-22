package Repository.ProductRepository;

import Entity.Product;

import java.io.*;
import java.util.Formatter;

public class AddProductService implements IAddProduct {

    private final File productFile = new File("src/main/resources/PRODUCT_LIST.txt");


    public boolean addProduct(Product product) {
        boolean productDuplicate = false;
        try {
            if (!productFile.exists()) productFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(productFile));
            String line;
            StringBuilder formatData = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int id = Integer.parseInt(lines[0]);
                if (id == product.getProductId() && product.getProductBrandName().equalsIgnoreCase(lines[1])) {
                    formatData.append(lines[0]).append(",")
                            .append(lines[1]).append(",")
                            .append(lines[2]).append(",")
                            .append(Integer.parseInt(lines[3]) + product.getProductQty()).append("\n");
                    productDuplicate = true;
                } else if (id == product.getProductId()) {
                    return false;
                } else {
                    formatData.append(line).append("\n");
                }
            }

            if (!productDuplicate) {
                formatData.append(product.getProductId()).append(",")
                        .append(product.getProductBrandName()).append(",")
                        .append(product.getProductPrice()).append(",")
                        .append(product.getProductQty()).append("\n");
            }


            Formatter formatter = new Formatter(productFile);
            formatter.format("%S", formatData);

            br.close();
            formatter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
