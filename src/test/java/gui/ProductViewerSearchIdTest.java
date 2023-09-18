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
import helpers.SimpleListModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ProductViewerSearchIdTest {
    
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product product1, product2, product3;
    
    @BeforeEach
    public void setUp() {
        // set up robot
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(50);

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

        // stub the searchById method to return the first product
        when(dao.searchById(product1.getProductId())).thenReturn(product1);

        // stub to populate the list with getProducts
        when(dao.getProducts()).thenReturn(products);

        
    }

    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }

    @Test
    public void testSearchById() {
        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // enter some details into the UI components
        fixture.textBox("txtSearchId").enterText(product1.getProductId());

        // click the save button
        fixture.button("btnSearch").click();

        // verify the searchById was called with the correct product Id
        verify(dao).searchById(product1.getProductId());

        // create simple model to check the list
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        // check the contents
        assertThat("make sure list has correct product", model, contains(product1));
        assertThat("Make sure list doesn't have product with wrong id", model, not(contains(product2)));
    }
}
