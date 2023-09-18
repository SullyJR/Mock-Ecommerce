<%-- 
    Document   : sign-in
    Created on : 22/08/2023, 7:13:06 pm
    Author     : callumsullivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Sign In</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Sign In</h1>

            <fieldset>

                <legend>Account Details</legend>
                
                <%
                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                    //so that it doesn't display if you leave the page and come back again
                    session.removeAttribute("validation");
                %>

                <p><%= validation%></p>

                <form action="sign-in" method="POST">
                    <label>Username:</label><input type="text" name="username" required/>
                    <label>Password:</label><input type="password" name="password" required/>
                    <button type="submit">Sign In</button>
                </form>

            </fieldset>


            <p>Don't have an account yet? Create one <a class="sign-up" href="sign-up.jsp">here</a></p>

        </main>
    </body>
</html>
