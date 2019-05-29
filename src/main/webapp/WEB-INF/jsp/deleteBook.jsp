<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${deleted eq true}">
        <span style="color: gray; font-size: 36px; "> Książka została usunięta </span>
        <a href="/library/">Powrót</a>
    </c:when>
    <c:otherwise>
        <span style="color: #801211; font-size: 36px; "> Książka nie została usunięta! </span>
        <a href="editBook">Powrót</a>
    </c:otherwise>
</c:choose>