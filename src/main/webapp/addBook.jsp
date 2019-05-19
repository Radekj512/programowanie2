<%@ page import="library.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="library.Category" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="library.utils.Validator" %>
<%@ page import="java.util.Comparator" %>

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
        <option><%= cat.getName()%>
        </option>
        <% }%>
    </select><br/>
    <input type="submit" name="submit" value="Dodaj">
</form>
<%} else {
    String title = request.getParameter("title");
    String isbn = request.getParameter("isbn");
    int year = Integer.parseInt(request.getParameter("year"));
    String cover = request.getParameter("cover");
    //Category category = categoryList.get(categoryList.indexOf(request.getParameter("category")));
    //Book bookWithMaxId = bookList.stream().max(java.util.Comparator.comparing(book -> book.getId())).orElse(null);
if (Validator.validateBook(title,isbn,year,cover,"1")){
        bookList.add(new Book(1, title, isbn, year, cover, null, null));}else{
    System.out.println("blad");
        }

}%>