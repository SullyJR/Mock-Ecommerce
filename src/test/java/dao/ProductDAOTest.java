/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author callumsullivan
 */
public class ProductDAOTest {

    private Product phone, earbuds, wipes;
    private ProductDAO dao;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        
        //dao = new ProductCollectionsDAO();
        dao = JdbiDaoFactory.getProductDAO();
        
        phone = new Product("P001", "Smartphone", "Latest model with high resolution camera", "phones", new BigDecimal("500.00"), new BigDecimal("20"));
        earbuds = new Product("P002", "Earbuds", "Wireless earbuds with noise cancellation", "audio", new BigDecimal("150.00"), new BigDecimal("30"));
        wipes = new Product("P003", "Wipes", "Antibacterial hand wipes", "Hygiene", new BigDecimal("5.99"), new BigDecimal("100"));
        dao.saveProduct(phone);
        dao.saveProduct(earbuds);
        //note not saving wips on purpose
    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(phone);
        dao.removeProduct(earbuds);
        dao.removeProduct(wipes);
    }

    @Test
    public void testSaveProduct() {
        //save jane using DAO
        dao.saveProduct(wipes);

        //retrieve student by ID
        Product retrievedProduct = dao.searchById("P003");

        //ensure correct student we got back
        assertThat("Retrieved product should be the same as the saved one",
                retrievedProduct, is(wipes));
    }

    @Test
    public void testRemoveProduct() {
        // make sure that phone does already exist and collection has right size
        assertThat(dao.getProducts(), hasItem(phone));
        assertThat(dao.getProducts(), hasSize(2));

        // delete phone
        dao.removeProduct(phone);

        // make sure that it is gone
        assertThat(dao.getProducts(), not(hasItem(phone)));
        //make sure still one product left not all gone
        assertThat(dao.getProducts(), hasSize(1));

        //make sure can delete product not already saved in 
        //dao without error 
        dao.removeProduct(wipes);
    }

    @Test
    public void testGetProducts() {
        //call getProducts
        Collection<Product> products = dao.getProducts();

        //ensure collection has both the itmes saved correctly
        assertThat(products, contains(phone, earbuds));
        assertThat(products, hasSize(2));
    }

    @Test
    public void testGetCategories() {
        //call getCatgeories
        Collection<String> categories = dao.getCategories();

        //make sure both categories exist
        assertThat(categories, containsInAnyOrder("phones", "audio"));

        //make sure random category doesn't exist
        assertThat(categories, not(contains("gaming")));

    }

    @Test
    public void testSearchById() {
        //get the product
        Product product = dao.searchById("P001");

        //assure it is the correct product
        assertThat(product, is(phone));

        //check correct null behaviour
        Product product2 = dao.searchById("P004");
        assertThat(product2, is(nullValue()));

    }

    @Test
    public void testFilterByCategory() {
        //get collection on phones category
        String category = "phones";
        Collection<Product> products = dao.filterByCategory(category);

        //make sure size is only 1
        assertThat(products, hasSize(1));

        //make sure products doesn't contain wrong item
        assertThat(products, not(contains(earbuds)));
    }

}
