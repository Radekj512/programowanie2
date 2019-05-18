package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class User {
    private static int id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public User(String name, String lastName, String email, String password) {
        this.id++;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addToFile() {
        String firstUser = id + ";" + name + ";" + lastName + ";" + email + ";" + password;
        String nextUser = "\n" + id + ";" + name + ";" + lastName + ";" + email + ";" + password;

        Path path = Paths.get("src", "main", "resources", "users.csv");
        try {
            if (Files.lines(path).toArray().length == 0) {
                Files.write(path, firstUser.getBytes(), StandardOpenOption.APPEND);
            }else{
                Files.write(path, nextUser.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
