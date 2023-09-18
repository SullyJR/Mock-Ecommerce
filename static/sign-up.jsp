<%-- 
    Document   : sign-up
    Created on : 22/08/2023, 7:05:51 pm
    Author     : callumsullivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Sign Up</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Create New Account</h1>

            <fieldset>

                <legend>Customer Details</legend>

                <%                    
                    String validation = (String) session.getAttribute("sign-up-validation");
                    validation = validation != null ? validation : "";
                    session.removeAttribute("sign-up-validation");
                %>

                <p><%= validation%></p>

                <form action="sign-up" method="POST">

                    <label>Username:</label><input type="text" name="username" required/>
                    <label>First Name:</label><input type="text" name="fname"/>
                    <label>Last Name:</label><input type="text" name="lname"/>
                    <label>Email:</label><input type="text" name="email"/>
                    <label>Address:</label><textarea type="text" name="address"></textarea>
                    <label>Password:</label><input type="password" name="password" required/>
                    <button type="submit">Create Account</button>

                </form>

            </fieldset>

            <p><a href="index.jsp">Back to Home</a></p>

        </main>
    </body>
</html>
