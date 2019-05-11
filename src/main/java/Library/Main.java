package Library;

import Library.Utils.LoadBooks;
import Library.Utils.LoadUsers;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static LoadBooks loadBooks = new LoadBooks();
    static List<Book> books = loadBooks.getList();
    private static LoadUsers loadUsers = new LoadUsers();
    static List<User> users = loadUsers.getList();

    public static void main(String[] args) {


        mainApp();

    }

    public static void mainApp() {
            if(signIn()) {
                Scanner scanner = new Scanner(System.in);
                int option = 0;
                do {
                    printMenu();
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            prinBooks();
                            break;
                        case 2:
                            printUsers();
                            break;
                        case 3:
                            addUser();
                    }
                } while (option != 4);
            }else{
                System.out.println("Podano złe dane logowania");
            }
    }

    public static void printMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("//--------------------------------------//\n");
        menu.append("//1. Wyświetl listę książek             //\n");
        menu.append("//2. Wyświetl listę użytkowników        //\n");
        menu.append("//3. Dodaj użytkownika                  //\n");
        menu.append("//4. Wyjście                            //\n");
        menu.append("//--------------------------------------//\n");
        menu.append("Wypierz opcje: ");
        System.out.print(menu);
    }

    public static void prinBooks() {
        if (!books.isEmpty()) {
            StringBuilder s = new StringBuilder();
            s.append("|\tTytył\t|\tIBSN\t|\tRok wydania\t|\n");
            books.forEach(book -> s.append("| " + book.getTitle() + "\t|\t" + book.getIbsn() + "\t|\t" + book.getYear() + "\t|\n"));
            System.out.println(s.toString());
        } else {
            System.out.println("Lista jest pusta");
        }
    }

    public static void printUsers() {
        if (!users.isEmpty()) {
            StringBuilder s = new StringBuilder();
            s.append("Imie\tNazwisko\tEmail\tHaslo\n");
            users.forEach(user -> s.append(user.getName() + "\t" + user.getLastName() + "\t" + user.getEmail() + "\t" + user.getPassword() + "\n"));
            System.out.println(s.toString());
        } else {
            System.out.println("Brak uzytkownikow w bazie");
        }
    }

    public static void addUser() {
        System.out.println("Dodawanie nowego użytkownika\n");
        String imie, nazwisko, email, haslo;
        Scanner s = new Scanner(System.in);
        System.out.print("Podaj imie: ");
        imie = s.next();
        System.out.print("Podaj nazwisko: ");
        nazwisko = s.next();
        System.out.print("Podaj email: ");
        email = s.next();
        System.out.print("Podaj haslo: ");
        haslo = s.next();
        User tmp = new User(imie, nazwisko, email, haslo);
        users.add(tmp);
        tmp.addToFile();
    }

    public static boolean signIn() {
        String tmpMail;
        String tmpPassword;
        Scanner s = new Scanner(System.in);
        System.out.print("Podaj adres email: ");
        tmpMail = s.next();
        System.out.print("Podaj haslo: ");
        tmpPassword = s.next();

        for (User user : users) {
            if (user.getEmail().equals(tmpMail) && user.getPassword().equals(tmpPassword)) {
                return true;
            }
        }
        return false;
    }
}