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
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author callumsullivan
 */
public class ProductEditorTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(15);

        // add some categories to test
        Collection<String> categories = new ArrayList<>();
        categories.add("electronics");
        categories.add("accessories");

        // create mock for the dao
        dao = mock(ProductDAO.class);

        //create stub for the getCategories method
        when(dao.getCategories()).thenReturn(categories);

    }

    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }

    @Test
    public void testSaveProduct() {
        //create dialog passing in the mocked dao
        ProductEditor dialog = new ProductEditor(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        //show dialog on screen
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // enter some details into the UI components
        fixture.textBox("txtId").enterText("1234");
        fixture.textBox("txtName").enterText("iphone");
        fixture.textBox("txtDescription").enterText("apple phone");
        fixture.comboBox("cmbCategory").selectItem("electronics");
        fixture.textBox("txtPrice").enterText("999.99");
        fixture.textBox("txtQuantity").enterText("100");

        // click the save button
        fixture.button("btnSave").click();

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed product
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();

        // test that the product's details were properly saved
        assertThat("Ensure the ID was saved", savedProduct, hasProperty("productId", equalTo("1234")));
        assertThat("Ensure the name was saved", savedProduct, hasProperty("name", equalTo("iphone")));
        assertThat("Ensure the description was saved", savedProduct, hasProperty("description", equalTo("apple phone")));
        assertThat("Ensure the price was saved", savedProduct, hasProperty("category", equalTo("electronics")));
        assertThat("Ensure the price was saved", savedProduct, hasProperty("listPrice", equalTo(new BigDecimal("999.99"))));
        assertThat("Ensure the stock was saved", savedProduct, hasProperty("quantityInStock", equalTo(new BigDecimal("100"))));

    }

}
