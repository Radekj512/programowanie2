package library.utils;

import library.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadUsers {
    private List<User> usersList = new ArrayList<>();
    /*{
        Path path = Paths.get("C:\\Programowanie\\Programowanie2\\src\\main\\resources\\users.csv");

        try {
            Files.lines(path).filter(line -> !line.isEmpty())
                    .map(line -> line.split(";"))
                    .forEach(splittedUserParameters -> usersList.add(new User(splittedUserParameters[1],splittedUserParameters[2],splittedUserParameters[3],splittedUserParameters[4])));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
            // e.printStackTrace();
        }
    }*/

    {
        String query = "SELECT * FROM users;";
        try {
            Connection connection = Connect_db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet i = statement.executeQuery(query);
            while (i.next()){
                usersList.add(new User(i.getString("name"), i.getString("lastName"), i.getString("email"), i.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<User> getList(){
        return usersList;
    }
}
