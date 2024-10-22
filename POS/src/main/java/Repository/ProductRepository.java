package Repository;

import Main.Product;

import java.io.*;
import java.util.ArrayList;

public class ProductRepository {

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            File file = new File("product.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int id = Integer.parseInt(lines[0]);
                String name = lines[1];
                double price = Double.parseDouble(lines[2]);
                int qty = Integer.parseInt(lines[3]);
                productList.add(new Product(id, name, price, qty));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return productList;
    }

    public static Product getProduct(int idChoice) {
        try {
            File file = new File("product.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int id = Integer.parseInt(lines[0]);
                String name = lines[1];
                double price = Double.parseDouble(lines[2]);
                int qty = Integer.parseInt(lines[3]);
                if (id == idChoice) {
                    return new Product(id, name, price, qty);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void addProductStock(int addId, int addStocks) {
        try {
            File file = new File("product.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int id = Integer.parseInt(lines[0]);
                if (id == addId) {
                    sb.append(lines[0]).append(",").append(lines[1]).append(",").append(lines[2]).append(",").append(Integer.parseInt(lines[3]) + addStocks).append("\n");
                } else {
                    sb.append(line).append("\n");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static boolean buyProduct(int idChoice, int qtyChoice) {
        try {
            File file = new File("product.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                int id = Integer.parseInt(lines[0]);
                int qty = Integer.parseInt(lines[3]);
                if (id == idChoice) {
                    if (qtyChoice > qty) {
                        return false;
                    }
                    qty -= qtyChoice;
                    sb.append(id).append(",").append(lines[1]).append(",").append(lines[2]).append(",").append(qty).append("\n");
                } else {
                    sb.append(line).append("\n");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return true;
    }
}
