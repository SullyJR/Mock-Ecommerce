/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Sale;
import domain.SaleItem;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

/**
 *
 * @author callumsullivan
 */
public interface SaleJdbiDAO extends SaleDAO {

	@SqlUpdate("INSERT INTO sale (customer_username, sale_date, sale_status) VALUES (:customer.username, :date, :status)")
	@GetGeneratedKeys
	Integer insertSale(@BindBean Sale sale);

	@SqlUpdate("INSERT INTO sale_item (sale_id, product_id, quantity, sale_price) VALUES (:saleId, :product.productId, :quantityPurchased, :salePrice)")
	void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

	@SqlUpdate("UPDATE product SET quantity_in_stock = quantity_in_stock - :quantityPurchased WHERE product_id = :product.productId")
	void updateStockLevel(@BindBean SaleItem item);

	@Override
	@Transaction
	default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());

        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
		Integer saleId = insertSale(sale);
		sale.setSaleId(saleId);

        // loop through the sale's items.
		for (SaleItem item : sale.getItems()) {
			insertSaleItem(item, saleId);
			updateStockLevel(item);
		}

	}
}
