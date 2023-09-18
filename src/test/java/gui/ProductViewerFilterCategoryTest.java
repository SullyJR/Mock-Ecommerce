package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.timing.Pause.pause;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductViewerFilterCategoryTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product product1, product2, product3;

    @BeforeEach
    public void setUp() {
        // set up robot
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(1000);

        // create mock for the dao
        dao = mock(ProductDAO.class);

        // create fake products for testing with
        Collection<Product> products = new ArrayList<>();
        product1 = new Product("1234", "name1", "desc1", "cat1", new BigDecimal("11.00"), new BigDecimal("22.00"));
        product2 = new Product("5678", "name2", "desc2", "cat1", new BigDecimal("33.00"), new BigDecimal("44.00"));
        product3 = new Product("9012", "name3", "desc3", "cat3", new BigDecimal("55.00"), new BigDecimal("66.00"));
        products.add(product1);
        products.add(product2);
        products.add(product3);

        // create fake product collection of one category for testing with
        // both products are in the same category so we can test that both are returned
        Collection<Product> productsInSameCategory = new ArrayList<>();
        productsInSameCategory.add(product1);
        productsInSameCategory.add(product2);
        
        // add collection containing all the categories to populate the combobox
        Collection<String> categories = new ArrayList<>();
        categories.add(product1.getCategory());
        categories.add(product2.getCategory());
        categories.add(product3.getCategory());
        
        // stub the getProducts method to return the test products
        when(dao.getProducts()).thenReturn(products);
        
        when(dao.getCategories()).thenReturn(categories);

        // stub the filterByCategory method to return the test products
        when(dao.filterByCategory(product1.getCategory())).thenReturn(productsInSameCategory);

    }

    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }

    @Test
    public void TestFilterCategory() {
        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // select the category radio button
        fixture.click();

        // select the category from the drop down list
        fixture.comboBox("cmbCategories").selectItem("cat1");

        // verify the filterByCategory method was Called
        verify(dao).filterByCategory(product1.getCategory());

        // create simple model to check the list
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        // check the contents
        assertThat("make sure list has correct product", model, containsInAnyOrder(product1, product2));
        assertThat("Make sure list doesn't have product with wrong id", model, not(contains(product3)));
    
    }

}
