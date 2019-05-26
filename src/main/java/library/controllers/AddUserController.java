package library.controllers;

import library.User;
import library.utils.Connect_db;
import library.utils.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddUserController {
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute("addUserForm") User user, BindingResult result) {

        return new ModelAndView("addUser");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addUserForm") User user, BindingResult result) {

        Map<String, Object> model = new HashMap<>();

        if (!result.hasErrors() && Validator.validateUser(user)) {
            String query = String.format("INSERT INTO `users` (`name`, `lastName`, `email`, `password`) VALUES (\"%s\", \"%s\", \"%s\", \"%s\");",
                    user.getName(), user.getLastName(), user.getEmail(),user.getPassword());

            try {
                Connection connection = Connect_db.getConnection();
                Statement statement = connection.createStatement();
                if(statement.executeUpdate(query) != 0){
                    model.put("newUser", user);
                    return new ModelAndView("newUser", model);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ModelAndView("newUser", model);
        } else {
            return new ModelAndView("newUser");
        }
    }
}
