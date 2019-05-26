<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<span style="color: gray; font-size: 36px; "> Dodawanie nowej książki </span>
<br/>

<table>
    <form:form modelAttribute="addBookForm" action="addBook" method="POST">
        <tr>
            <td>Podaj tytuł książki:</td>
            <td><form:input path="title" id="title"/></td>
        </tr>
        <tr>
            <td>Podaj ISBN:</td>
            <td><form:input path="ibsn" id="isbn"/></td>
        </tr>
        <tr>
            <td>Podaj rok wydania:</td>
            <td><form:input path="year" id="year"/></td>
        </tr>
        <tr>
            <td>Wybierz rodzaj okładki(T/M):</td>
            <td><form:input path="binding" id="binding" maxlength="1"/></td>
        </tr>
        <tr>
            <td>Wybierz kategorie:</td>
            <td><form:select items="${catList}" path="category" multiple="false"/></td>
        </tr>
        <tr>
            <td>Wybierz autorów:</td>
            <td>
                    <form:checkboxes path="authors" items="${authors}"/>
        </tr>
        <tr>
            <td><form:button type="submit" id="add-book">Dodaj książkę</form:button></td>
        </tr>
    </form:form>
</table>
