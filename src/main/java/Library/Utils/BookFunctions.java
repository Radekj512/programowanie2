package library.utils;

import library.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookFunctions {

    public Book findIsbn(String isbn, List<Book> bookList) {
        Book tmpBook = null;
        for (Book book : bookList) {
            if (book.getIbsn().equalsIgnoreCase(isbn)) {
                tmpBook = book;
            }
        }
        return tmpBook;

    }

    public Book findIsbnStram(String isbn, List<Book> bookList) {
        return bookList.stream().filter(book -> book.getIbsn().equalsIgnoreCase(isbn)).findFirst().orElse(null);
    }

    public List<Book> getLastTwoBooks(List<Book> bookList) {
        List<Book> returnList = new ArrayList<>();
        if (bookList.size() < 2) {
            return null;
        }
        for (int i = bookList.size()-1; i > bookList.size() - 3; i--) {
            returnList.add(bookList.get(i));
        }
        return returnList;
    }

    public List<Book> getLastTwoBooksStream(List<Book> bookList) {
        return bookList.stream().skip(bookList.size() - 2).collect(Collectors.toList());
    }

    public Book findOldestBook(List<Book> bookList) {
        Book oldest = bookList.get(0);
        for (Book book : bookList) {
            if (book.getYear() < oldest.getYear()) {
                oldest = book;
            }
        }
        return oldest;
    }

    public Book findOldestStream(List<Book> bookList) {
        Comparator<Book> compareYear = Comparator.comparing(Book::getYear);
        return bookList.stream().min(compareYear).orElse(null);
    }

    public Book findYoungestBook(List<Book> bookList) {
        Book youngest = bookList.get(0);
        for (Book book : bookList) {
            if (book.getYear() > youngest.getYear()) {
                youngest = book;
            }
        }
        return youngest;
    }

    public Book findYoungestBookStream(List<Book> bookList) {
        Comparator<Book> compareYear = Comparator.comparing(Book::getYear);
        return bookList.stream().max(compareYear).orElse(null);
    }

    public int sumOfYears(List<Book> bookList) {
        int sum = 0;
        for (Book book : bookList) {
            sum = sum + book.getYear();
        }
        return sum;
    }

    public int sumOfYearsStream(List<Book> bookList) {
        return bookList.stream().mapToInt(Book::getYear).sum();
    }

    public int howManyBooksAfter2007(List<Book> bookList) {
        int numberOfBooks = 0;
        for (Book book : bookList) {
            if (book.getYear() > 2007) {
                numberOfBooks++;
            }
        }
        return numberOfBooks;
    }

    public int howManyBooksAfter2007Stream(List<Book> bookList) {
        return (int) bookList.stream().filter(book -> book.getYear() > 2007).count();
    }

    public boolean isAllBooksAreNewerThan2000(List<Book> bookList) {
        for (Book book : bookList) {
            if (book.getYear() < 2000) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllBooksAreNewerThan2000Stream(List<Book> bookList) {
        return bookList.stream().filter(book -> book.getYear() < 2000).count() > 1;
    }

    public double averageOfBookYears(List<Book> bookList) {
        double sumOfYears = sumOfYears(bookList);
        double booksCount = bookList.size();
        return sumOfYears / booksCount;
    }

    public double averageOfBookYearsStream(List<Book> bookList) {
        return bookList.stream().mapToDouble(Book::getYear).sum() / bookList.size();
    }

    public boolean isThereAnyBookBefore2003(List<Book> bookList) {
        for (Book book : bookList) {
            if (book.getYear() < 2003) {
                return true;
            }
        }
        return false;
    }

    public boolean isThereAnyBookBefore2003Stream(List<Book> bookList) {
        return bookList.stream().anyMatch(book -> book.getYear() < 2003);
    }
}
