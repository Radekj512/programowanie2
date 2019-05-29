package library.controllers;

import library.Book;
import library.utils.Connect_db;
import library.utils.LoadAuthors;
import library.utils.LoadBooks;
import library.utils.LoadCategories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EditBookController {
    private Book bookToEdit;
    @RequestMapping(value = "/editBook", method = RequestMethod.GET)
    public ModelAndView booksList(ModelAndView modelAndView) {
        List<Book> booksFromDatabase = new LoadBooks().getList();
        modelAndView.addObject("books", booksFromDatabase);
        return modelAndView;
    }

    @RequestMapping(value = "/editBook/{id}", method = RequestMethod.GET)
    public ModelAndView editBook(@PathVariable("id") int id) {

        String query = String.format("SELECT * FROM books WHERE `ID` = %d;", id);
        Connection connection = Connect_db.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                 bookToEdit = new Book(
                         resultSet.getInt("ID"),
                         resultSet.getString("title"),
                         resultSet.getString("isbn"),
                         resultSet.getInt("year"),
                         resultSet.getString("binding"),
                         new LoadBooks().getAuthors(resultSet.getString("authors_ids")),
                         new LoadBooks().getCategory(resultSet.getInt("category_id"))
                 );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map<String, Object> model = new HashMap<>();
        model.put("bookEdit", bookToEdit);
        model.put("catList", new LoadCategories().getCategoriesList());
        model.put("authors", new LoadAuthors().getAuthorsList());
        return new ModelAndView("bookEditForm", model);
    }

    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable("id") int id) {
        Map<String, Object> model = new HashMap<>();
        String query = String.format("DELETE FROM `books` WHERE `books`.`ID` = %d", id);
        Connection connection = Connect_db.getConnection();

        try {
            Statement statement = connection.createStatement();
            int isExecuted = statement.executeUpdate(query);

            if (isExecuted > 0){
                model.put("deleted", true);
            }else {
                model.put("deleted", false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("deleteBook", model);
    }





}
