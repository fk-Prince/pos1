package Main;

import Repository.*;

import java.util.List;
import java.util.Scanner;

public class Services {
    private Scanner sc = new Scanner(System.in);

    Services() {

        run();
    }

    private void run() {
        while (true) {
            displayServices();
        }
    }

    public void displayServices() {
        System.out.println("------------------");
        System.out.print("[1]ADD PRODUCT STOCKS\n[2]BUY PRODUCT\n[3]GENERATE SALES\n[4]EXIT\nCHOICES: ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> addService();
            case 2 -> buyService();
            case 3 -> {
                double totalOverallSales = new GenerateSales().computeSales();
                System.out.println("Overall Total Sales is " + totalOverallSales);
            }
            case 4 -> System.exit(0);
        }
    }


    public void addService() {
        getProductList();

        System.out.print("Enter Product Id you would like to add: ");
        int idChoice = Integer.parseInt(sc.nextLine());
        if (idChoice < 4 && idChoice > 0) {
            System.out.print("Enter how many quantity you want to add: ");
            int qty = Integer.parseInt(sc.nextLine());
            if (qty > 0) {
                ProductRepository.addProductStock(idChoice, qty);
                System.out.println("Stocks Added\n");
            } else {
                System.out.println("Cant be below zero or zero");
            }
        }
    }

    public void getProductList() {
        List<Product> productList = ProductRepository.getProducts();
        if (productList != null) {
            productList.forEach(Product::displayProduct);
        } else {
            System.out.println("No products available.");
        }
    }

    public void buyService() {
        getProductList();

        System.out.print("Enter Product Id you would like to buy: ");
        int idChoice = Integer.parseInt(sc.nextLine());

        Product productChoice = ProductRepository.getProduct(idChoice);

        if (productChoice != null) {
            System.out.print("Enter how many quantity you want to buy: ");
            int qty = Integer.parseInt(sc.nextLine());
            if (qty > 0) {
                if (ProductRepository.buyProduct(idChoice, qty)) {
                    Payment payment = new Payment(productChoice.getId(), productChoice.getName(), productChoice.getPrice(), qty);
                    System.out.println("Total of " + payment.compute());
                    PaymentRepository.addPayments(payment);
                    System.out.println("Purchased successfully");
                } else {
                    System.out.println("Out of Stock");
                }
            } else {
                System.out.println("Greater than 0");
            }
        }
    }
}
