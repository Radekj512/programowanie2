package Library.Utils;

import Library.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LoadBooks {
    private List<Book> booksList = new ArrayList<>();
     {
        Path path = Paths.get("src", "main", "resources", "books.csv");

        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(book -> booksList.add(new Book(book[0], book[1], Integer.valueOf(book[2]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
           // e.printStackTrace();
        }
    }
    public List<Book> getList(){
        return booksList;
    }
}
