package Frame.Frames;


import Entity.Product;
import Entity.Payment;
import Frame.Interfaces.ProductSelected;
import Frame.Interfaces.TableRefresh;
import SwingComponents.MyButton;
import SwingComponents.MyTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.Queue;


public class BuyItem extends JPanel implements ProductSelected {

    private MyTextField productName, productPrice, productQty, productId;
    private TableRefresh refreshTable;
    private final Queue<Payment> cartProduct;

    public BuyItem() {
        cartProduct = new LinkedList<>();
        setLayout(new MigLayout("fill, insets 0 0 0 0", "[0]0[0]", "[0]0[0]"));
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {requestFocusInWindow();}
            @Override
            public void focusLost(FocusEvent e) {}
        });
        setBackground(Color.GREEN);
        init();
    }

    private void init() {
        productId = new MyTextField("PICK PRODUCT ID. . .", false);
        productId.setFocusable(false);
        productName = new MyTextField("PICK PRODUCT NAME. . .", false);
        productName.setFocusable(false);
        productPrice = new MyTextField("PICK PRODUCT PRICE. . .", false);
        productPrice.setFocusable(false);
        productQty = new MyTextField("PRODUCT QUANTITY. . .", true);

        MyButton buy = new MyButton("ADD TO CART");
        buy.setPreferredSize(new Dimension(260, 70));
        buy.addActionListener(e -> {
            if (productName.getStringText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Choose an Item");
                return;
            }
            if (!productQty.isIntValid() || !productId.isIntValid()) {
                JOptionPane.showMessageDialog(null, "Invalid Input");
                return;
            }
            Payment payment = new Payment(productId.getIntText(), productName.getStringText(), productPrice.getDoubleText(), productQty.getIntText());
            if (refreshTable.isPaymentValid(payment)) {
                JOptionPane.showMessageDialog(null, "Added to Cart");
                cartProduct.add(payment);
                refreshTable.refreshTable();
                productName.setText("");
                productId.setText("");
                productPrice.setText("");
                productQty.setText("");
            }
        });
        MyButton clear = new MyButton("CLEAR");
        clear.setPreferredSize(new Dimension(200, 40));
        clear.addActionListener(e -> {
            clearFields();
            refreshTable.refreshTableData();
        });

        JButton button = new JButton();
        button.addActionListener(e -> new Cart(cartProduct, refreshTable));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        Image image = new ImageIcon(getClass().getResource("/Image/cart.png")).getImage();
        button.setIcon(new ImageIcon(image));


        add(button, "pos 2% 2%, w 50!, h 50!,wrap");
        add(productId, "pos 15% 15%, w 70%!,wrap");
        add(productName, "pos 15% 30%, w 70%!,wrap");
        add(productPrice, "pos 15% 45%, w 70%!,wrap");
        add(productQty, "pos 15% 60%, w 70%!,wrap");
        add(buy, "pos 30% 80%, w 40%!,wrap");
        add(clear, "pos 40% 92%, w 20%!");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(223, 217, 217));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString("BUY ITEM", (getWidth() - fm.stringWidth("BUY ITEM")) / 2, 50);


        g2d.dispose();
    }


    @Override
    public void setProduct(Product product) {
        if (product != null) {
            productId.setText(String.valueOf(product.getProductId()));
            productName.setText(product.getProductBrandName());
            productPrice.setText(String.valueOf(product.getProductPrice()));
        }
    }

    public void clearFields() {
        cartProduct.clear();
        productId.setText("");
        productName.setText("");
        productPrice.setText("");
        productQty.setText("");
    }

    public void setListener(TableRefresh refreshTable) {
        this.refreshTable = refreshTable;
    }
}
