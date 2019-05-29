package library.controllers;

import library.Book;
import library.utils.Connect_db;
import library.utils.LoadAuthors;
import library.utils.LoadBooks;
import library.utils.LoadCategories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Controller
public class BookController {

    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
    public ModelAndView books(ModelAndView modelAndView) {
         List<Book> booksFromDatabase = new LoadBooks().getList();
        modelAndView.addObject("books", booksFromDatabase);
        return modelAndView;
    }
    @RequestMapping(value = "/bookList", params = {"sortby"}, method = RequestMethod.GET)
    public @ResponseBody ModelAndView booksSorted(@RequestParam(value = "sortby") String sort, ModelAndView modelAndView) {
        List<Book> sortedList = new LinkedList<>();
        String query = String.format("SELECT * FROM `books` ORDER BY `books`.`%s` ASC;", sort);
        Connection connection = Connect_db.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                sortedList.add(new Book(
                        resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("year"),
                        resultSet.getString("binding"),
                        new LoadBooks().getAuthors(resultSet.getString("authors_ids")),
                        new LoadBooks().getCategory(resultSet.getInt("category_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("books", sortedList);
        return modelAndView;
    }

}
