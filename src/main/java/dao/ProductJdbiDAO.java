/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author callumsullivan
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlQuery ("select * from product where product_id = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String id);

    @Override
    @SqlUpdate ("insert into product (product_id, name, description, category, list_price, quantity_in_stock)"
            + "values(:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    @RegisterBeanMapper(Product.class)
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlUpdate ("delete from product where product_id = :productId")
    public void removeProduct(@BindBean Product product);

    @Override
    @SqlQuery ("select * from product order by product_id")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();

    @Override
    @SqlQuery ("select distinct category from product")
    public Collection<String> getCategories();

    @Override
    @SqlQuery ("select * from product where category = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);
    
}
