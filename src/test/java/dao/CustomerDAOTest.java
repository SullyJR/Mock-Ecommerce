/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerDAOTest {

    private Customer jane, bob, alice;
    private CustomerDAO dao;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        
        //dao = new CustomerCollectionsDAO();
        dao = JdbiDaoFactory.getCustomerDAO();
        
        jane = new Customer("JaneDoe", "Jane", "Doe", "jane@email.com", "123 Street", "password1");
        bob = new Customer("BobSmith", "Bob", "Smith", "bob@email.com", "456 Street", "password2");
        alice = new Customer("AliceJohnson", "Alice", "Johnson", "alice@email.com", "789 Street", "password3");
        dao.saveCustomer(jane);
        dao.saveCustomer(bob);
        //note not saving alice on purpose
    }

    @AfterEach
    public void tearDown() {
        dao.removeCustomer(jane);
        dao.removeCustomer(bob);
        dao.removeCustomer(alice);
    }

    @Test
    public void testSaveCustomer() {
        //save alice using DAO
        dao.saveCustomer(alice);

        //retrieve customer by username
        Customer retrievedCustomer = dao.getCustomerByUsername("AliceJohnson");

        //ensure correct customer we got back
        assertThat("Retrieved customer should be the same as the saved one",
                retrievedCustomer, is(alice));
    }

    @Test
    public void testRemoveCustomer() {
        // make sure that jane does already exist
        assertThat(dao.getCustomerByUsername("JaneDoe"), is(jane));

        // delete jane
        dao.removeCustomer(jane);

        // make sure that she is gone
        assertThat(dao.getCustomerByUsername("JaneDoe"), is(nullValue()));

        // make sure can delete customer not already saved without error 
        dao.removeCustomer(alice);
    }

    @Test
    public void testGetCustomerByUsername() {
        // get the customer
        Customer customer = dao.getCustomerByUsername("JaneDoe");

        // assure it is the correct customer
        assertThat(customer, is(jane));

        // check correct null behaviour for nonexistent customer
        Customer customer2 = dao.getCustomerByUsername("NonExistentUser");
        assertThat(customer2, is(nullValue()));
    }

    @Test
    public void testCheckCredentials() {
        // check for valid credentials
        boolean valid = dao.validateCredentials("JaneDoe", "password1");
        assertThat(valid, is(true));

        // make sure invalid password works correctly
        boolean invalidPassword = dao.validateCredentials("JaneDoe", "wrongPassword");
        assertThat(invalidPassword, is(false));

        // make sure nonexistent user cannot login
        boolean nonexistentUser = dao.validateCredentials("NonExistentUser", "password1");
        assertThat(nonexistentUser, is(false));
    }
}
