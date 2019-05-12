package Library.Utils;

import Library.Author;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoadAuthors {
    private List<Author> authors = new ArrayList<>();

    {
        Path path = Paths.get("src", "main", "resources", "authors.csv");
        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(author -> authors.add(new Author(Integer.parseInt(author[0]), author[1], Integer.parseInt(author[2]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
//            e.printStackTrace();
        }
    }
    public List<Integer> getAuthorsIds(){
        return authors.stream().map(author -> author.getId()).collect(Collectors.toList());
    }
    public List<Author> getAuthorsList(){
        return authors;
    }
}
