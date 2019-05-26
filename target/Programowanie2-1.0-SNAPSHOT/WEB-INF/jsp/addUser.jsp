<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<span style="color: gray; font-size: 36px; "> Dodawanie nowego użytkownika </span>
<br/>
<table>
    <form:form modelAttribute="addUserForm" action="addUser" method="POST">
    <tr>
        <td>Imie:</td>
        <td><form:input path="name" id="name"/></td>
        <td><form:errors path="name" cssclass="error" /></td>
    </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><form:input path="lastName" id="lastName"/></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><form:input path="email" id="email"/></td>
        </tr>
        <tr>
            <td>Hasło:</td>
            <td><form:password path="password" id="password" /></td>
        </tr>
        <tr>
        <td><form:button type="submit" id="add-user">Dodaj nowego użytkownika</form:button></td>
        </tr>
    </form:form>
</table>