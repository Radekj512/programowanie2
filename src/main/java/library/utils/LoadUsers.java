package library.utils;

import library.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadUsers {
    private List<User> usersList = new ArrayList<>();
    {
        Path path = Paths.get("D:\\Projekty_java\\Programowanie2\\src\\main\\resources\\users.csv");

        try {
            Files.lines(path).filter(line -> !line.isEmpty())
                    .map(line -> line.split(";"))
                    .forEach(splittedUserParameters -> usersList.add(new User(splittedUserParameters[1],splittedUserParameters[2],splittedUserParameters[3],splittedUserParameters[4])));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
            // e.printStackTrace();
        }
    }
    public List<User> getList(){
        return usersList;
    }
}
