<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${newBook['valid']}">
        <span style="color: gray; font-size: 36px; "> Książka została dodana! </span>
        <a href="/library/">Powrót</a>
    </c:when>
    <c:otherwise>
        <span style="color: #801211; font-size: 36px; "> Książka nie została dodana! </span>
        <a href="addBook">Powrót</a>
    </c:otherwise>
</c:choose>

