package Frame.Frames;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Entity.*;
import Repository.PaymentRepository.TotalSalesService;
import SwingComponents.MyButton;
import net.miginfocom.swing.MigLayout;

public class GenerateSalesUI extends JPanel {
    private JFrame salesFrame;

    public GenerateSalesUI() {
        init();
    }

    private void init() {
        if (salesFrame == null) {
            salesFrame = new JFrame("Total Sales");
            salesFrame.setSize(1000, 500);
            salesFrame.setLocationRelativeTo(null);
            salesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            salesFrame.toFront();
        }

        JScrollPane scroll = new JScrollPane(viewSales());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        salesFrame.add(scroll);
        salesFrame.setVisible(true);
    }

    public JPanel viewSales() {
        setLayout(new MigLayout("insets 20, fillx"));

        JLabel headerTitle = new JLabel("TOTAL GENERATED SALES");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 40));

        JLabel headerId = new JLabel("Product Id");
        JLabel headerItem = new JLabel("Product Name");
        JLabel headerQty = new JLabel("Quantity");
        JLabel headerPrice = new JLabel("Price");
        JLabel headerTotal = new JLabel("Total");

        Font headerFont = new Font("Arial", Font.BOLD, 16);
        headerId.setFont(headerFont);
        headerItem.setFont(headerFont);
        headerQty.setFont(headerFont);
        headerPrice.setFont(headerFont);
        headerTotal.setFont(headerFont);

        add(headerTitle, "cell 0 0,al center, span 6");
        add(headerId, "cell 0 1,al left");
        add(headerItem, "cell 1 1,al left");
        add(headerQty, "cell 2 1, al right");
        add(headerPrice, "cell 3 1, al right");
        add(headerTotal, "cell 4 1, al right, wrap");

        List<Payment> salesList = new TotalSalesService().getOverallSales();

        if (!salesList.isEmpty()) {
            int row = 2;
            for (Payment payment : salesList) {
                JLabel itemId = new JLabel(String.valueOf(payment.getProductId()));
                JLabel itemName = new JLabel(payment.getProductBrandName());
                JLabel itemQty = new JLabel(String.valueOf(payment.getProductQty()));
                JLabel itemPrice = new JLabel(String.format("%.2f", payment.getProductPrice()));
                JLabel totalPrice = new JLabel(String.format("%.2f", payment.compute()));

                add(itemId, "cell 0 " + row + ", al left");
                add(itemName, "cell 1 " + row + ", al left");
                add(itemQty, "al right, cell 2 " + row);
                add(itemPrice, "al right, cell 3 " + row);
                add(totalPrice, "al right, cell 4 " + row + ", wrap");

                row++;
            }

            JLabel totalLabel = new JLabel("Total Generated Sales: " + String.format("%.2f", new GenerateSales().computeSales()));
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(totalLabel, "cell 3 " + row + ", span 2, al right");

            MyButton closeButton = new MyButton("Close");
            closeButton.addActionListener(e -> salesFrame.dispose());
            closeButton.setPreferredSize(new Dimension(200, 60));
            add(closeButton, "cell 0 " + (row + 1) + ", al left");
        } else {
            JLabel label = new JLabel("No Sales");
            label.setFont(new Font("Arial", Font.BOLD, 30));
            add(label, "ax 50%, ay 50%, span 5");
        }

        return this;
    }
}
