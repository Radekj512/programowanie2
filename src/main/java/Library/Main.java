package Library;

import Library.Utils.LoadBooks;
import Library.Utils.LoadUsers;
import Library.Utils.SaveBooks;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static LoadBooks loadBooks = new LoadBooks();
    static List<Book> books = loadBooks.getList();
    private static LoadUsers loadUsers = new LoadUsers();
    static List<User> users = loadUsers.getList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        mainApp();

    }

    public static void mainApp() {

//        boolean signedIn = false;
//        if (!users.isEmpty()) {
//            do {
//                signedIn = signIn();
//            } while (!signedIn);
//        }
        int option = 0;
        do {
            printMenu();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    prinBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    editBookYear();
                    break;
                case 5:
                    saveBooksToCsv();
                    break;
                case 6:
                    printUsers();
                    break;
                case 7:
                    addUser();
                    break;
                default:
                    printMenu();
                    break;
            }
        } while (option != 8);


    }

    public static void printMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("//--------------------------------------//\n");
        menu.append("//1. Wyświetl listę książek.             //\n");
        menu.append("//2. Dodanie nowej książki.              //\n");
        menu.append("//3. Usunięcie książki po nazwie.        //\n");
        menu.append("//4. Edycja roku wydania książki.        //\n");
        menu.append("//5. Zapisz listę książek do pliku csv.  //\n");
        menu.append("//6. Wyświetl listę użytkowników.        //\n");
        menu.append("//7. Dodaj użytkownika.                  //\n");
        menu.append("//8. Wyjście.                            //\n");
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
        System.out.print("Podaj imie: ");
        imie = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        nazwisko = scanner.nextLine();
        System.out.print("Podaj email: ");
        email = scanner.nextLine();
        System.out.print("Podaj haslo: ");
        haslo = scanner.nextLine();
        User tmp = new User(imie, nazwisko, email, haslo);
        users.add(tmp);
        tmp.addToFile();
    }

    public static boolean signIn() {
        String tmpMail;
        String tmpPassword;
        System.out.print("Podaj adres email: ");
        tmpMail = scanner.nextLine();
        System.out.print("Podaj haslo: ");
        tmpPassword = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().trim().equals(tmpMail.trim()) && user.getPassword().trim().equals(tmpPassword.trim())) {
                return true;
            }
        }
        return false;
    }

    public static void addBook() {
        System.out.println("Dodawanie nowej książki\n");
        String title;
        String ibsn;
        int year;
        System.out.print("Podaj tytuł ksiązki: ");
        title = scanner.nextLine();
        System.out.print("Podaj ibsn ksiązki: ");
        ibsn = scanner.nextLine();
        System.out.print("Podaj rok wydania ksiązki: ");
        year = Integer.parseInt(scanner.nextLine());

        books.add(new Book(title, ibsn, year));
    }

    public static void deleteBook() {
        System.out.println("Usuwanie ksiązki po tytule.\n");
        System.out.println("Podaj tytuł ksiązki do usunięcia: ");
        String title = scanner.nextLine();
        boolean deleteSuccess = books.removeIf(book-> book.getTitle().trim().equals(title.trim()));
        if (deleteSuccess){
            System.out.println("Ksiązka " + title + " została usunięta");
        }else{
            System.out.println("Podana książka nie istnieje");
        }

    }

    public static void editBookYear() {
        System.out.println("Zmiana roku wydania książki:\n");
        System.out.println("Podaj tytuł książki");
        String title = scanner.nextLine();

        if(books.stream().filter(book->book.getTitle().trim().equals(title)).count() >= 1){
            System.out.print("Podaj nowy rok wydania: ");
            int newYear = Integer.parseInt(scanner.nextLine());
            books.stream().filter(book-> book.getTitle().trim().equals(title)).forEach(book-> book.setYear(newYear));
            System.out.println("Rok wydania ksiązki " + title + " został zmieniony");
        }else{
            System.out.println("Podanej książki nie ma w bazie");
        }



    }

    public static void saveBooksToCsv() {
        System.out.println("Zapisywanie listy ksiązek do pliku\n");
        System.out.print("Podaj nazwe pliku który chcesz zapisac(bez .csv; istniejący plik zostane zastąpiony): ");
        String fileName = scanner.nextLine();
        Path path = Paths.get("src", "main", "resources", fileName+".csv");
        if(SaveBooks.saveBooksToCsv(books, path)){
            System.out.println("Lista została zapisana do pliku " + path.getFileName());
        }
    }


}