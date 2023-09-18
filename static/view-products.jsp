<%-- 
    Document   : view-products
    Created on : 22/08/2023, 9:02:15 pm
    Author     : callumsullivan
--%>

<%@page import="dao.DaoFactory"%>
<%@page import="dao.ProductCollectionsDAO"%>
<%@page import="dao.ProductDAO"%>
<%@page import="domain.Product"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Products</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Products</h1>
            <div class="categories">
                <a href="view-products.jsp?category=All"><button>All</button></a>

                <%                    
                    ProductDAO dao = DaoFactory.getProductDAO();

                    Collection<String> categories = dao.getCategories();

                    for (String category : categories) {
                %>

                <a href="view-products.jsp?category=<%= category%>"><button><%= category%></button></a>

                <%
                    }
                %>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // get the category from the query parameter
                        String selectedCategory = request.getParameter("category");

                        // declare the products collection
                        Collection<Product> products;

                        // if there is no cateogory parameter, or "All" is requested, return all students
                        if (selectedCategory == null || selectedCategory.equals("All")) {
                            products = dao.getProducts();
                        } else {
                            // otherwise, get the students for the requested major
                            products = dao.filterByCategory(selectedCategory);
                        }

                        for (Product product : products) {
                    %>
                    <tr>
                        <td><%= product.getName()%></td>
                        <td><%= product.getDescription()%></td>
                        <td><%= product.getListPrice()%></td>
                        <td><%= product.getQuantityInStock()%></td>
                        <td><button>Buy</button></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p><a class="nav" href="index.jsp">Back to Home</a></p>

        </main>
    </body>
</html>
