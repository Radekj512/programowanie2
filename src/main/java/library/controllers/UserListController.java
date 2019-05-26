package library.controllers;

import library.User;
import library.utils.LoadUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserListController {

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public ModelAndView books(ModelAndView modelAndView) {
        List<User> users = new LoadUsers().getList();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
