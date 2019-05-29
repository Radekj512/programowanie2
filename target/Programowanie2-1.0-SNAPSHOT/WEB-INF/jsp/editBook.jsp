<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" %>

<table border="1">
    <tr>
        <td>ID</td>
        <td>Tytul</td>
        <td>ISBN</td>
        <td>Rok wydania</td>
        <td>Rodzaj okladki</td>
        <td>Autorzy</td>
        <td>Kategoria</td>
        <td>Edytuj książkę</td>
        <td>Usuń książkę</td>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.title}" /></td>
            <td><c:out value="${book.ibsn}" /></td>
            <td><c:out value="${book.year}" /></td>
            <td><c:out value="${book.binding}" /></td>
            <td><c:out value="${book.authors}" /></td>
            <td><c:out value="${book.category}" /></td>
            <td><a href="editBook/${book.id}">Zmień</a></td>
            <td><a href="deleteBook/${book.id}">Usuń</a> </td>
        </tr>
    </c:forEach>

</table>
