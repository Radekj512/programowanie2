package library.utils;

import library.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadAuthors {
    private List<Author> authors = new ArrayList<>();


    {

        String query = "SELECT * FROM authors;";
        try {
            Connection connection = Connect_db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                authors.add(new Author(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*{

        Path path = Paths.get("C:\\Programowanie\\Programowanie2\\src\\main\\resources\\authors.csv");

        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(author -> authors.add(new Author(Integer.parseInt(author[0]), author[1], Integer.parseInt(author[2]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
//            e.printStackTrace();
        }
    }*/
    public List<Integer> getAuthorsIds(){
        return authors.stream().map(author -> author.getId()).collect(Collectors.toList());
    }
    public List<Author> getAuthorsList(){
        return authors;
    }
}
