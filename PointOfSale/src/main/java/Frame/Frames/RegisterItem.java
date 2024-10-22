package Frame.Frames;

import Entity.Product;
import Frame.Interfaces.ProductSelected;
import Frame.Interfaces.TableRefresh;
import Repository.ProductRepository.AddProductService;
import Repository.ProductRepository.IAddProduct;
import SwingComponents.MyButton;
import SwingComponents.MyTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class RegisterItem extends JPanel implements ProductSelected {

    private TableRefresh refreshTable;
    private MyTextField productName, productPrice, productQty, productId;
    private ProductSelected productSelected;
    private final IAddProduct iAddProduct;

    public RegisterItem() {
        iAddProduct = new AddProductService();
        setLayout(new MigLayout("fill, insets 0 0 0 0", "[0]0[0]", "[0]0[0]"));
        init();
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
        g2d.drawString("REGISTER ITEM", (getWidth() - fm.stringWidth("REGISTER ITEM")) / 2, 50);


        g2d.dispose();
    }

    private void init() {
        productId = new MyTextField("ITEM ID. . .", true);
        productName = new MyTextField("ITEM NAME. . .", true);
        productPrice = new MyTextField("ITEM PRICE. . .", true);
        productQty = new MyTextField("ITEM QUANTITY. . .", true);

        MyButton register = new MyButton("ADD");
        register.setPreferredSize(new Dimension(260, 70));
        register.addActionListener(e -> {
            if (productName.getStringText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty Input");
                return;
            }
            if (!productId.isIntValid() || !productPrice.isPriceValid() || !productQty.isIntValid() || productName.getStringText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
                return;
            }
            if (iAddProduct.addProduct(new Product(productId.getIntText(), productName.getStringText(), productPrice.getDoubleText(), productQty.getIntText()))) {
                JOptionPane.showMessageDialog(this, "Product Added");
            } else {
                JOptionPane.showMessageDialog(this, "PRODUCT ID IS ALREADY REGISTERED, TRY AGAIN ANOTHER");
                return;
            }
            clearFields();
            refreshTable.refreshTableData();
        });

        MyButton clear = new MyButton("Clear");
        clear.setPreferredSize(new Dimension(200, 40));
        clear.addActionListener(e -> clearFields());

        add(productId, "pos 15% 15%, w 70%!,wrap");
        add(productName, "pos 15% 30%, w 70%!,wrap");
        add(productPrice, "pos 15% 45%, w 70%!,wrap");
        add(productQty, "pos 15% 60%, w 70%!,wrap");
        add(register, "pos 30% 80%, w 40%!,wrap");
        add(clear, "pos 40% 92%, w 20%!");
    }


    public void clearFields() {
        productId.setText("");
        productName.setText("");
        productPrice.setText("");
        productQty.setText("");
    }

    public void setListener(TableRefresh refreshTable) {
        this.refreshTable = refreshTable;
    }

    @Override
    public void setProduct(Product product) {
        if (productId != null) {
            productId.setText(String.valueOf(product.getProductId()));
            productName.setText(product.getProductBrandName());
            productPrice.setText(String.valueOf(product.getProductPrice()));
            // productQty.setText(String.valueOf(product.getProductQty()));
        }
    }
}

