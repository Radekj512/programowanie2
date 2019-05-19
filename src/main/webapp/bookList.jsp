<%@ page import="library.Book" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:useBean id="test" class="library.utils.LoadBooks" scope="session"/>
    <jsp:setProperty property="*" name="test"/>
    <% List<Book> bookList = test.getList(); %>
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

        <% for (Book book : bookList) { %>
        <tr>
            <td><%=book.getId()%>
            </td>
            <td><%=book.getTitle()%>
            </td>
            <td><%=book.getIbsn()%>
            </td>
            <td><%=book.getYear()%>
            </td>
            <td><%=book.getBinding()%>
            </td>
            <td><%=book.getAuthors()%>
            </td>
            <td><%=book.getCategory()%>
            </td>
        </tr>
        <%}%>
    </table>

