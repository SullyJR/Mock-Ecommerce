/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;

/**
 *
 * @author callumsullivan
 */
public interface CustomerDAO extends CredentialsValidator {
    
    void saveCustomer(Customer customer);
    
    void removeCustomer(Customer customer);
    
    Customer getCustomerByUsername(String username);
    
}
