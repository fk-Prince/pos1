package Frame;

import Entity.Payment;
import Interfaces.TableRefresh;
import Repository.BuyProductService;
import SwingComponents.MyButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class Cart extends JPanel {
    private final Queue<Payment> cartProduct;
    private JFrame cartFrame;
    private final TableRefresh refreshTable;

    public Cart(Queue<Payment> cartProduct, TableRefresh refreshTable) {
        this.refreshTable = refreshTable;
        this.cartProduct = cartProduct;
        init();
    }

    public void init() {
        if (cartFrame == null) {
            cartFrame = new JFrame("Receipt");
            cartFrame.setSize(1000, 500);
            cartFrame.setLocationRelativeTo(null);
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            cartFrame.toFront();
        }

        JScrollPane scroll = new JScrollPane(receipts());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        cartFrame.add(scroll);
        cartFrame.setVisible(true);
    }

    public JPanel receipts() {
        setLayout(new MigLayout("insets 20, fillx"));

        JLabel IdItem = new JLabel("Product Id");
        JLabel headerItem = new JLabel("Product Name");
        JLabel headerQty = new JLabel("Quantity");
        JLabel headerPrice = new JLabel("Price");
        JLabel headerTotal = new JLabel("Total");

        Font headerFont = new Font("Arial", Font.BOLD, 16);
        IdItem.setFont(headerFont);
        headerItem.setFont(headerFont);
        headerQty.setFont(headerFont);
        headerPrice.setFont(headerFont);
        headerTotal.setFont(headerFont);

        add(IdItem, "cell 0 0,al left");
        add(headerItem, "cell 1 0,al left");
        add(headerQty, "cell 2 0, al right");
        add(headerPrice, "cell 3 0, al right");
        add(headerTotal, "cell 4 0, al right, wrap");


        if (!cartProduct.isEmpty()) {
            int row = 1;
            for (Payment cartProduct : this.cartProduct) {
                JLabel itemId = new JLabel(String.valueOf(cartProduct.getProductId()));
                JLabel itemName = new JLabel(cartProduct.getProductBrandName());
                JLabel itemQty = new JLabel(String.valueOf(cartProduct.getProductQty()));
                JLabel itemPrice = new JLabel(String.valueOf(cartProduct.getProductPrice()));
                JLabel totalPrice = new JLabel(String.valueOf(cartProduct.compute()));


                add(itemId, "cell 0 " + row + ", al left");
                add(itemName, "cell 1 " + row + ",al left");
                add(itemQty, "al right, cell 2 " + row);
                add(itemPrice, "al right, cell 3 " + row);
                add(totalPrice, "al right, cell 4 " + row + ", wrap");

                row++;
            }


            JLabel totalLabel = new JLabel("Total: " + String.format("%.2f", calculateTotal()));
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(totalLabel, "cell 3 " + row + ", span 2, al right");

            MyButton button = new MyButton("BUY");
            button.addActionListener(e -> {
                boolean success = false;
                for (Payment pay : cartProduct) {
                    success = new BuyProductService().doPayment(pay);
                }
                if (success) {
                    cartFrame.dispose();
                    JOptionPane.showMessageDialog(this, "PURCHASED SUCCESSFULLY");
                }
                refreshTable.refreshTableData();
            });
            button.setPreferredSize(new Dimension(200, 60));
            add(button, "cell 3 " + row + 2 + ", span 2, al right");

            MyButton remove = new MyButton("Remove All and Exit");
            remove.addActionListener(e -> {
                cartProduct.clear();
                cartFrame.dispose();
                refreshTable.refreshTableData();
            });
            remove.setPreferredSize(new Dimension(200, 60));
            add(remove, "cell 0 " + row + 2 + ", al left");
        } else {
            JLabel label = new JLabel("NO ITEMS IN THE CART");
            label.setFont(new Font("Arial", Font.BOLD, 30));
            add(label, "ax 50%, ay 50%, span 5");
        }

        return this;
    }

    private double calculateTotal() {
        double total = 0;
        for (Payment product : cartProduct) {
            total += product.compute();
        }
        return total;
    }
}
