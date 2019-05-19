package library.utils;

import library.Category;
import library.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadCategories {
    private List<Category> categoriesList = new ArrayList<>();

    {

        String query = "SELECT * FROM categories;";
        try {
            Connection connection = Connect_db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet i = statement.executeQuery(query);
            while (i.next()) {
                categoriesList.add(new Category(i.getInt("ID"), i.getString("name"), i.getInt("priority")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*{
        Path path = Paths.get("C:\\Programowanie\\Programowanie2\\src\\main\\resources\\categories.csv");
        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(category -> categories.add(new Category(Integer.parseInt(category[0]), category[1], Integer.parseInt(category[2]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
//            e.printStackTrace();
        }
    }*/
    public List<Category> getCategoriesList() {
        return categoriesList;
    }
}
