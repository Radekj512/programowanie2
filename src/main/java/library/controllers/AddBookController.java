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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AddBookController {

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView createBook(@ModelAttribute("addBookForm") Book book, BindingResult result) {
        Map<String, Object> model = new HashMap<>();

        model.put("catList", new LoadCategories().getCategoriesList());
        model.put("authors", new LoadAuthors().getAuthorsList());


        return new ModelAndView("addBook", model);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addBookForm") Book book, BindingResult result, Model mod) {

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("newBook", new Book());
        model.put("catList", new LoadCategories().getCategoriesList());
        model.put("authors", new LoadAuthors().getAuthorsList());
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


            System.out.println("dodano");

            return new ModelAndView("addBook", model);
        } else {
            populateDefaultModel(mod);
            System.out.println("Nie dodano");
            return new ModelAndView("addBook", (Map<String, ?>) mod);
        }
    }

    private void populateDefaultModel(Model model) {

        List<Category> categoryList = new LoadCategories().getCategoriesList();
        List<Author> authorList = new LoadAuthors().getAuthorsList();
        model.addAttribute("catList", categoryList);
        model.addAttribute("authors", authorList);

    }


}






