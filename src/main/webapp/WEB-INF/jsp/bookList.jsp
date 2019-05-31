<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" %>

<table border="1">
    <tr>
        <td><a href="bookList?sortby=ID">ID</td>
        <td><a href="bookList?sortby=title"> Tytul</a></td>
        <td><a href="bookList?sortby=isbn">ISBN</a></td>
        <td><a href="bookList?sortby=year">Rok wydania</a></td>
        <td><a href="bookList?sortby=binding">Rodzaj okladki</a></td>
        <td>Autorzy</td>
        <td>Kategoria</td>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.title}" /></td>
            <td><c:out value="${book.ibsn}" /></td>
            <td><c:out value="${book.year}" /></td>
            <td><c:out value="${book.printBinding()}" /></td>
            <td><c:out value="${book.authors}" /></td>
            <td><c:out value="${book.category}" /></td>
        </tr>
    </c:forEach>

</table>

