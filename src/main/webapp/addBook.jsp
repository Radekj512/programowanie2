<%@ page import="library.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="library.Category" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="library.utils.Validator" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="library.utils.Connect_db" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<span style="color: gray; font-size: 36px; "> Dodawanie nowej książki </span>
<br/>
<jsp:useBean id="books" class="library.utils.LoadBooks" scope="application"/>
<jsp:setProperty property="*" name="books"/>
<jsp:useBean id="categories" class="library.utils.LoadCategories" scope="session"/>
<jsp:setProperty name="categories" property="*"/>
<% List<Book> bookList = books.getList();
    List<Category> categoryList = categories.getCategoriesList();
%>

<% if (request.getParameter("submit") == null) {%>
<form action="" method="post">
    Podaj tytuł książki: <input type="text" name="title"><br/>
    Podaj ISBN: <input type="text" name="isbn"><br/>
    Podaj rok wydania: <input type="number" name="year"><br/>
    Podaj rodzaj oprawy(T/M): <input type="text" name="cover" maxlength="1"><br/>
    Wybierz kategorie:
    <select name="category">
        <%
            for (Category cat : categoryList) {%>
        <option><%=cat.getName()%></option>
        <% }%>
    </select><br/>
    <input type="submit" name="submit" value="Dodaj">
</form>
<%
    } else {

        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        int year = Integer.parseInt(request.getParameter("year"));
        String cover = request.getParameter("cover");
        Category cat = null;
        for (Category c : categoryList) {
            if (c.getName().equalsIgnoreCase(request.getParameter("category"))){
                cat = c;
            }
        }
        int catId = categoryList.indexOf(cat)+1;

        String query = "INSERT INTO books(title, isbn, year, binding, authors_ids, category_id) VALUES('" + title + "','" + isbn + "'," + year + ",'" + cover + "', 1," + catId + ");";

        if (Validator.validateBook(title, isbn, year, cover, "1")) {
            //bookList.add(new Book(1, title, isbn, year, cover, null, null));}else{
            Connection connection = Connect_db.getConnection();
            Statement statement = null;
            try {
                statement = connection.createStatement();
                if(statement.executeUpdate(query) > 0){
                    %>Dodano nowa książkę<%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            %>Wprowadzono zle dane<%
        }

    }
%>