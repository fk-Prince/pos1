package Repository;

import Main.Payment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SalesRepository {

    public static ArrayList<Payment> getTotalSales() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        try {

            File file = new File("payment.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                paymentList.add(new Payment(Integer.parseInt(lines[0]), lines[1], Double.parseDouble(lines[2]), Integer.parseInt(lines[3])));
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentList;
    }
}
