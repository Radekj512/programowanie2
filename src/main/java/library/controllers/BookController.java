package library.controllers;

import library.Author;
import library.Book;
import library.Category;
import library.utils.Connect_db;
import library.utils.LoadAuthors;
import library.utils.LoadCategories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private List<Book> booksFromDatabase = new ArrayList<>();
    private List<Category> categoryList = new LoadCategories().getCategoriesList();
    private List<Author> authors = new LoadAuthors().getAuthorsList();

    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
    public ModelAndView books(ModelAndView modelAndView) {

        String query = "SELECT * FROM books;";

        Connection connection = Connect_db.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                booksFromDatabase.add(new Book(resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("year"),
                        resultSet.getString("binding"),
                        getAuthors(resultSet.getString("authors_ids")),
                        getCategory(resultSet.getInt("category_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("books", booksFromDatabase);
        return modelAndView;
    }

    private Category getCategory(int id) {
        return categoryList.stream().filter(category -> category.getId() == id).findFirst().orElse(new Category(0,"0",0));
    }

    public List<Author> getAuthors(String authorsArrayIds) {
        return Arrays.stream(authorsArrayIds.split(","))
                .map(Integer::parseInt)
                .map(this::findAuthor)
                .collect(Collectors.toList());
    }
    private Author findAuthor(int id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

}
