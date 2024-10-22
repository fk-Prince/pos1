package Interfaces;

import Entity.ProductEntity;

public interface TableRefresh {

    void refreshTableData();

    void setEnabled(boolean bln);

    boolean isPaymentValid(ProductEntity product);

    void refreshTable();
}
