package library.utils;

import library.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SaveBooks {
    public static boolean saveBooksToCsv(List<Book> books, Path filePath) {
        List<String> listToWrite = new ArrayList<>();
        books.forEach(book -> listToWrite.add(book.toCsv()));
        File tmpFile = new File(filePath.toString());
        try {
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
            Files.write(filePath, listToWrite, StandardOpenOption.CREATE);
            return true;

        } catch (IOException e) {
            System.out.println("Failed to load file");
            return false;

        }
    }
}
