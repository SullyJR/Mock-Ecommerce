/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author callumsullivan
 */
public class CustomerModule extends Jooby {
    
    public CustomerModule(CustomerDAO dao) {
        
        post("/api/register", ctx -> {
            return ctx.send(StatusCode.CREATED);
        });
        
        get("/api/customers/{username}", ctx -> {
            
            String username = ctx.path("username").value();
            
            Customer customer = dao.getCustomerByUsername(username);
            
            if(customer == null){
                // no customer with that username so return 404
		return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return customer;
            }
            
        });
    
    }
}
