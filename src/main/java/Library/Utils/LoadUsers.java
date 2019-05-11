package Library.Utils;

import Library.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadUsers {
    private List<User> usersList = new ArrayList<>();
    {
        Path path = Paths.get("src", "main", "resources", "users.csv");

        try {
            Files.lines(path).map(line -> line.split(";"))
                    .forEach(user -> usersList.add(new User(user[0],user[1],user[2],user[3])));
        } catch (IOException e) {
            System.out.println("Failed to load file " + path.getFileName().toString());
            // e.printStackTrace();
        }
    }
    public List<User> getList(){
        return usersList;
    }
}
