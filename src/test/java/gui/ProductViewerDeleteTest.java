package gui;

import dao.ProductDAO;
import domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author callumsullivan
 */
public class ProductViewerDeleteTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product product1, product2, product3;

    @BeforeEach
    public void setUp() {
        // set up robot
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(15);

        // create fake products for testing with
        Collection<Product> products = new ArrayList<>();
        product1 = new Product("1234", "name1", "desc1", "cat1", new BigDecimal("11.00"), new BigDecimal("22.00"));
        product2 = new Product("5678", "name2", "desc2", "cat2", new BigDecimal("33.00"), new BigDecimal("44.00"));
        product3 = new Product("9012", "name3", "desc3", "cat3", new BigDecimal("55.00"), new BigDecimal("66.00"));
        products.add(product1);
        products.add(product2);
        products.add(product3);

        // create mock for the dao
        dao = mock(ProductDAO.class);

        // stub the getProducts method to return the test products
        when(dao.getProducts()).thenReturn(products);

        // stub the delete method and add behaviour that actually deletes a student
        Mockito.doAnswer((call) -> {
            // remove product1 from collection that getProducts() uses
            products.remove(product1);
            return null;
        }).when(dao).removeProduct(product1);

    }

    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }

    @Test
    public void testDelete() {
        // create dialog
        ProductViewer dialog = new ProductViewer(null, true, dao);

        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        fixture.click();

        // verify that the getAllProducts method was called
        verify(dao).getProducts();

        // select product1 in the list
        fixture.list("lstProducts").selectItem(product1.toString());

        // click the delete button
        fixture.button("btnDelete").click();

        // click the confirm button
        fixture.optionPane().requireVisible().yesButton().click();

        // verify deleted
        verify(dao).removeProduct(product1);

        // ensure the list has 1 product
        fixture.list("lstProducts").requireItemCount(2);

    }
}
