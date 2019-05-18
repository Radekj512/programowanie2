package library.utils;

import library.Author;
import library.Book;
import library.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class LoadBooks {
    private List<Book> booksList = new ArrayList<>();
    private List<Author> authors = new LoadAuthors().getAuthorsList();
    private List<Category> categories = new LoadCategories().getCategoriesList();

    {
        Path path = Paths.get("src", "main", "resources", "a.csv");

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
            System.out.println("Failed to load file " + path.getFileName().toString());
            // e.printStackTrace();
        }
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

    private Category getCategory(String id){

        return categories.stream().filter(category -> category.getId() == Integer.parseInt(id)).findFirst().orElse(null);
    }

    public List<Book> getList() {
        return booksList;
    }
}
