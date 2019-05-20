<%@ page import="library.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="library.utils.Connect_db" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="test" class="library.utils.LoadBooks" scope="session"/>
<jsp:setProperty property="*" name="test"/>
<% String query = "SELECT * FROM books;";
    Connection connection = Connect_db.getConnection();
    Statement statement = null;
    ResultSet resultSet = null;
    try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
    } catch (SQLException e) {
        e.printStackTrace();
    }



%>
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

    <% while(resultSet.next()) { %>
    <tr>
        <td><%=resultSet.getInt("ID")%>
        </td>
        <td><%=resultSet.getString("title")%>
        </td>
        <td><%=resultSet.getString("isbn")%>
        </td>
        <td><%=resultSet.getInt("year")%>
        </td>
        <td><%=resultSet.getString("binding")%>
        </td>
        <td><%=resultSet.getString("authors_ids")%>
        </td>
        <td><%=resultSet.getInt("category_id")%>
        </td>
    </tr>
    <%}%>
</table>

