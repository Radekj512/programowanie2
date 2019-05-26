package library.controllers;

import library.Book;
import library.utils.LoadBooks;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
    public ModelAndView books(ModelAndView modelAndView) {
         List<Book> booksFromDatabase = new LoadBooks().getList();
        modelAndView.addObject("books", booksFromDatabase);
        return modelAndView;
    }

}
