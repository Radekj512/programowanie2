<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<
<table border="1">
    <tr>
        <td>L.p.</td>
        <td>Tytul</td>
        <td>ISBN</td>
        <td>Rok wydania</td>
        <td>Rodzaj okladki</td>
        <td>Autorzy</td>
        <td>Kategoria</td>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="$(book.id)" /></td>
            <td><c:out value="$(book.title)" /></td>
            <td><c:out value="$(book.isbn)" /></td>
            <td><c:out value="$(book.year)" /></td>
        </tr>
    </c:forEach>

</table>

