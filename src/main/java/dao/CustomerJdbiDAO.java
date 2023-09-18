/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import domain.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author callumsullivan
 */
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlQuery ("select exists (select * from customer where username = :username and password = :password)")
    public boolean checkCredentials(@Bind("username") String username,  @Bind("password") String password);

    @Override
    @SqlQuery ("select * from customer where username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getCustomerByUsername(@Bind("username") String username);

    @Override
    @SqlUpdate ("delete from customer where username = :username")
    public void removeCustomer(@BindBean Customer customer);

    @Override
    @SqlUpdate ("insert into customer(username, first_name, surname, email_address, shipping_address, password)"
            + "values(:username, :firstName, :surname, :emailAddress, :shippingAddress, :password)")
    public void saveCustomer(@BindBean Customer customer);
    
}
