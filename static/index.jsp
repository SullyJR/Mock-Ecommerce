<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Home Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Welcome to the Product Store!</h1>
            <nav>
                <img class="photos" src="./photos/headphones.jpeg"/>
                <img class="photos" src="./photos/phone.jpeg"/>
            </nav>
            <h2>We sell a variety of products to satisfy your needs!</h2>
        </main>
    </body>
</html>
