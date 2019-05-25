package library.controllers;

import library.Book;
import library.Category;
import library.utils.LoadAuthors;
import library.utils.LoadCategories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AddBookController {

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView createBook(@ModelAttribute("addBookForm") Book book, BindingResult result) {
        String query = "SELECT * FROM categories;";
        List<Category> categoryList = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();

        model.put("catList", new LoadCategories().getCategoriesList());
        model.put("authors", new LoadAuthors().getAuthorsList());


        return new ModelAndView("addBook", model);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addBookForm") Book book, BindingResult result) {
        System.out.println(book);
        System.out.println(result);
        if (!result.hasErrors() ) {

            Map<String, Object> model = new HashMap<String, Object>();

            model.put("newBook", new Book());
            model.put("catList", new LoadCategories().getCategoriesList());
            model.put("authors", new LoadAuthors().getAuthorsList());
            System.out.println("dodano");
            return new ModelAndView("addBook", model);
        } else {
            System.out.println("Nie dodano");
            return new ModelAndView("addBook");
        }
    }
}






