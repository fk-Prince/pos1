package Repository.PaymentRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import Entity.Payment;

public class TotalSalesService implements  ITotalSales{
    private final File paymentFile = new File("src/main/resources/PAYMENT_LIST.txt");

    public List<Payment> getOverallSales() {
        List<Payment> paymentList = new ArrayList<>();
        try {
            if (!paymentFile.exists()) paymentFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(paymentFile));
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