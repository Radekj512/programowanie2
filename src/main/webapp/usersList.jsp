<%@ page import="java.util.List" %>
<%@ page import="library.Book" %>
<%@ page import="library.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <jsp:useBean id="users" class="library.utils.LoadUsers" scope="session"></jsp:useBean>
    <jsp:setProperty property="*" name="test"/>
    <% List<User> userList = users.getList(); %>
    <table border="1">
        <tr>
            <td>Imie</td>
            <td>Nazwisko</td>
            <td>E-mail</td>
        </tr>

        <% for (User user : userList) { %>
        <tr>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getLastName()%>
            </td>
            <td><%=user.getEmail()%>
            </td>

        </tr>
        <%}%>


    </table>