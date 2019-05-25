<%@ page import="library.Book" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="library.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Księgarnia</title>
</head>
<body>
<jsp:useBean id="books" class="library.utils.LoadBooks" scope="application"/>
<jsp:setProperty property="*" name="books"/>
<jsp:useBean id="categories" class="library.utils.LoadCategories" scope="session"/>
<jsp:setProperty name="categories" property="*"/>

<center>
    <span style="color: gray; font-size: 48px; "> Księgarnia </span>
    <br /><br />
    <table border="2">
        <tr>
            <td><a href="bookList">Lista Książek</a></td>
            <td><a href="addBook">Dodaj nową książkę</a></td>
            <td><a href="editBook">Edycja roku wydania książki</a></td>
            <td><a href="deleteBook">Usuń książkę po nazwie</a></td>
            <td><a href="usersList">Wyświetl listę użytkowników</a></td>
            <td><a href="addUser">Dodaj użytkownika</a></td>
        </tr>
    </table>

    <br/><br/><br/><br/>



</center>
</body>
</html>