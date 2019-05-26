<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${newUser['valid']}">
        <span style="color: gray; font-size: 36px; "> Nowy użytkownik został dodany! </span>
        <table>
            <tr>
                <td>Imie: </td>
                <td>${newUser.name}</td>
            </tr>
            <tr>
                <td>Naziwsko: </td>
                <td>${newUser.lastName}</td>
            </tr>
            <tr>
                <td>E-mail: </td>
                <td>${newUser.email}</td>
            </tr>
            <tr>
                <td><a href="/library/">Powrót</a> </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <span style="color: #801211; font-size: 36px; "> Nowy użytkownik nie został dodany! </span>
        <a href="addUser">Powrót</a>
    </c:otherwise>
</c:choose>


