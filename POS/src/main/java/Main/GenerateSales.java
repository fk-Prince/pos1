package Main;

import Repository.SalesRepository;

import java.util.List;

public class GenerateSales{
    private final List<Payment> listOfPayments;
    public GenerateSales() {
        listOfPayments = SalesRepository.getTotalSales();
    }


    public double computeSales(){
        return listOfPayments.stream().mapToDouble(Payment::compute).sum();
    }
}
