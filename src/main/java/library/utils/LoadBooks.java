package library.utils;

import library.Author;
import library.Book;
import library.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class LoadBooks {
    private List<Book> booksList = new ArrayList<>();
    private List<Author> authors = new LoadAuthors().getAuthorsList();
    private List<Category> categories = new LoadCategories().getCategoriesList();

    {

        String query = "SELECT * FROM books;";
        try {
            Connection connection = Connect_db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int i = 1;
            while (resultSet.next()) {

                booksList.add(new Book(
                        resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("year"),
                        resultSet.getString("binding"),
                        getAuthors(resultSet.getString("authors_ids")),
                        getCategory(resultSet.getInt("category_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*//Path path = Paths.get("src", "main", "resources", "books.csv");
        Path path = Paths.get("C:\\Programowanie\\Programowanie2\\src\\main\\resources\\books.csv");

        try {
            //List<Author> authorsList = new ArrayList<>();

            Files.lines(path).map(line -> line.split(";"))
                    .forEach(book -> booksList.add(new Book(Integer.parseInt(book[0]),
                            book[1],
                            book[2],
                            Integer.valueOf(book[3]),
                            book[4],
                            getAuthors(book[5]),
                            getCategory(book[6]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString() + "  " + e.getMessage());
             e.printStackTrace();
        }*/
    }

    public List<Author> getAuthors(String authorsArrayIds) {
        return Arrays.stream(authorsArrayIds.split(","))
                .map(Integer::parseInt)
                .map(this::findAuthor)
                .collect(Collectors.toList());
    }



    private Author findAuthor(int id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public Category getCategory(int id){

        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    public List<Book> getList() {
        return booksList;
    }
}