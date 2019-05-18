package library.utils;

import library.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadCategories {
    private List<Category> categories = new ArrayList<>();

    {
        Path path = Paths.get("src", "main", "resources", "categories.csv");
        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(category -> categories.add(new Category(Integer.parseInt(category[0]), category[1], Integer.parseInt(category[2]))));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
//            e.printStackTrace();
        }
    }
    public List<Category> getCategoriesList(){
        return categories;
    }
}
