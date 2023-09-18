/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;

/**
 *
 * @author callumsullivan
 */
public interface CustomerDAO {
    
    void saveCustomer(Customer customer);
    
    void removeCustomer(Customer customer);
    
    Customer getCustomerByUsername(String username);
    
    boolean checkCredentials(String username, String password);
    
}
