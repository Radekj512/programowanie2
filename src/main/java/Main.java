
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static LoadBooks loadBooks = new LoadBooks();
    static List<Book> books = loadBooks.getList();

    public static void main(String[] args) {

        mainApp();
    }

    public static void mainApp() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            printMenu();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    prinBooks();
                    break;
            }
        } while (option != 2);
    }

    public static void printMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("//--------------------------------------//\n");
        menu.append("//1. Wyświetl listę książek             //\n");
        menu.append("//2. Wyjście                            //\n");
        menu.append("//--------------------------------------//\n");
        menu.append("Wypierz opcje: ");
        System.out.print(menu);
    }

    public static void prinBooks() {
        StringBuilder s = new StringBuilder();
        s.append("|\tTytył\t|\tIBSN\t|\tRok wydania|\n");

        books.forEach(book -> s.append("| " + book.getTitle() + "\t|\t" + book.getIbsn() + "\t|\t" + book.getYear() + "\t|\n"));

        System.out.println(s.toString());
    }
}