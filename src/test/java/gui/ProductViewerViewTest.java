/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import helpers.SimpleListModel;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author callumsullivan
 */
public class ProductViewerViewTest {
    
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product product1, product2;
    
    @BeforeEach
    public void setUp() {
        //set up robot
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(15);

        // create fake products for testing with
        Collection<Product> products = new ArrayList<>();
        product1 = new Product("1234", "name1", "desc1", "cat1", new BigDecimal("11.00"), new BigDecimal("22.00"));
        product2 = new Product("5678", "name2", "desc2", "cat2", new BigDecimal("33.00"), new BigDecimal("44.00"));
        products.add(product1);
        products.add(product2);

        // create mock for the dao
        dao = mock(ProductDAO.class);

        // stub the getProducts method to return the test products
        when(dao.getProducts()).thenReturn(products);

    }
    
    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }

    @Test
    public void testView() {
        ProductViewer dialog = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        fixture.click();

        //verify that the getAllProducts method was called
        verify(dao).getProducts();

        // get the model
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        // check the contents
        assertThat("list contains the correct number of products", model.getSize(), equalTo(2));
        assertThat("contains the correct items", model, containsInAnyOrder(product1, product2));

    }
    
}
