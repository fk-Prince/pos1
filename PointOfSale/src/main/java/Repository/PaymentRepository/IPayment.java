package Repository.PaymentRepository;

import Entity.Payment;

public interface IPayment {
    boolean doPayment(Payment p);
}
