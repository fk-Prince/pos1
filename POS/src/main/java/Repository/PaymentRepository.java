package Repository;

import Main.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PaymentRepository {

    public static void addPayments(Payment payment) {
        try {
            File file = new File("payment.txt");
            if (!file.exists()) file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(payment.getId() + "," + payment.getName() + "," + payment.getPrice() + "," + payment.getQuantity());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
