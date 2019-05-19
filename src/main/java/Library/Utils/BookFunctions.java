package library.utils;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Bool;
import library.Book;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

    public Book findIsbnStream(String isbn, List<Book> bookList) {
        return bookList.stream().filter(book -> book.getIbsn().equalsIgnoreCase(isbn)).findFirst().orElse(null);
    }

    public List<Book> getLastTwoBooks(List<Book> bookList) {
        List<Book> returnList = new ArrayList<>();
        if (bookList.size() < 2) {
            return null;
        }
        for (int i = bookList.size() - 1; i > bookList.size() - 3; i--) {
            returnList.add(bookList.get(i));
        }
        return returnList;
    }

    public List<Book> getLastTwoBooksStream(List<Book> bookList) {
        return bookList.stream().skip(bookList.size() - 2).collect(toList());
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

    public List<Book> getBooksWithTitleStartingWithCAndYoungerThan2007(List<Book> bookList) {
        return bookList.stream().filter(book -> book.getTitle().startsWith("C")).filter(book -> book.getYear() > 2007).collect(toList());
    }

    public List<Book> add100YearsToEveryBook(List<Book> bookList) {
        List<Book> tmpList = bookList;
        tmpList.forEach(book -> book.setYear(book.getYear() + 100));
        return tmpList;
    }

    public Map<String, Book> getIsbnBookMap(List<Book> bookList) {
        Map<String, Book> booksMap = new HashMap<>();
        bookList.forEach(book -> booksMap.put(book.getIbsn(), book));
        return booksMap;
    }

    public void sortBooksByYearAscending(List<Book> bookList) {
        Comparator<Book> comparator = Comparator.comparingInt(Book::getYear);
        bookList.sort(comparator);
    }

    public void sortBooksByYearDescending(List<Book> bookList) {
        Comparator<Book> comparator = Comparator.comparingInt(Book::getYear).reversed();
        bookList.sort(comparator);
    }

    public List<List<Book>> get3Lists(List<Book> bookList) {
        List<List<Book>> retList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i = i + 2) {
            retList.add(bookList.subList(i, i + 2));
        }
        //retList = Lists.partition(bookList, 2); GUAVA
        return retList;
    }

    public Map<Integer, List<Book>> getYearBookMap(List<Book> bookList) {
        Map<Integer, List<Book>> retMap = new HashMap<>();
        for (Book book : bookList) {
            List<Book> tmpList = new ArrayList<>();
            bookList.stream().filter(b -> b.getYear() == book.getYear()).forEach(tmpList::add);
            retMap.put(book.getYear(), tmpList);
        }
        return retMap;
    }

    public Map<Boolean, List<Book>> getBooksAfter2009Map(List<Book> bookList) {
        return bookList.stream().collect(groupingBy(book -> book.getYear() > 2009, mapping(Function.identity(), toList())));
    }

}
