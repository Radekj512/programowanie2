package library.controllers;

import library.Author;
import library.Book;
import library.Category;
import library.utils.Connect_db;
import library.utils.LoadAuthors;
import library.utils.LoadCategories;
import library.utils.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AddBookController {




    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView createBook(@ModelAttribute("addBookForm") Book book, BindingResult result) {
        Map<String, Object> model = new HashMap<>();
        List<Author> authorsList = new LoadAuthors().getAuthorsList();
        List<Category> categoriesList = new LoadCategories().getCategoriesList();

        model.put("catList", categoriesList);
        model.put("authors", authorsList);


        return new ModelAndView("addBook", model);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addBookForm") Book book, BindingResult result, Model mod) {

        List<Author> authorsList = new LoadAuthors().getAuthorsList();
        List<Category> categoriesList = new LoadCategories().getCategoriesList();
        Map<String, Object> model = new HashMap<String, Object>();

        setFields(book, authorsList, categoriesList);

        model.put("newBook", book);
        model.put("catList", categoriesList);
        model.put("authors", authorsList);

//book.getCategory().get();
        if (!result.hasErrors() && Validator.validateBook(book)) {
                String query = String.format("INSERT INTO books(title, isbn, year, binding, authors_ids, category_id) VALUES (\"%s\",\"%s\",%d,\"%s\",\"%s\",%d);", book.getTitle(), book.getIbsn(), book.getYear(), book.getBinding(), book.getAuthorsIds(), book.getCategory().getId());

            try {
                Connection connection = Connect_db.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ModelAndView("newBook", model);
        } else {
            populateDefaultModel(mod);
            System.out.println("Nie dodano");
            return new ModelAndView("newBook", (Map<String, ?>) mod);
        }
    }

    private void setFields(@ModelAttribute("addBookForm") Book book, List<Author> authorsList, List<Category> categoriesList) {
        Category tmpCat = book.getCategory();
        tmpCat.setId(categoriesList.get(categoriesList.indexOf(tmpCat)).getId());
        tmpCat.setPriority(categoriesList.get(categoriesList.indexOf(tmpCat)).getPriority());
        book.setCategory(tmpCat);

        List<Author> tmpAuthors = book.getAuthors();
        tmpAuthors.forEach(author -> author.setAge(authorsList.get(authorsList.indexOf(author)).getAge()));
        tmpAuthors.forEach(author -> author.setId(authorsList.get(authorsList.indexOf(author)).getId()));
        book.setAuthors(tmpAuthors);
    }

    private void populateDefaultModel(Model model) {

        List<Category> categoryList = new LoadCategories().getCategoriesList();
        List<Author> authorList = new LoadAuthors().getAuthorsList();
        model.addAttribute("catList", categoryList);
        model.addAttribute("authors", authorList);

    }


}






