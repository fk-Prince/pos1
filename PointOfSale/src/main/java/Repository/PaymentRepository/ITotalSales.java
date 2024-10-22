package Repository.PaymentRepository;

import Entity.Payment;

import java.util.List;

public interface ITotalSales {
    List<Payment> getOverallSales();
}
