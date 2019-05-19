package library.utils;

import library.Book;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class BookFunctionsTest {

    List<Book> bookList = new ArrayList<>();
    BookFunctions sut = new BookFunctions();

    @Before
    public void setUp() {
        bookList.add(new Book(1, "pierwsza", "123", 2003, "m", null, null));
        bookList.add(new Book(2, "druga", "456", 1998, "m", null, null));
        bookList.add(new Book(3, "trzecia", "789", 2008, "m", null, null));
        bookList.add(new Book(4, "czwarta", "012", 2007, "m", null, null));
        bookList.add(new Book(5, "piata", "345", 2010, "m", null, null));
        bookList.add(new Book(6, "szosta", "678", 2000, "m", null, null));
    }


    @Test
    public void findIsbn() {
        assertEquals(bookList.get(0), sut.findIsbn("123", bookList));
    }

    @Test
    public void findIsbnStram() {
        assertEquals(bookList.get(1), sut.findIsbnStream("456", bookList));
        assertNotEquals(bookList.get(0), sut.findIsbnStream("456", bookList));
    }

    @Test
    public void getLastTwoBooks() {
        assertThat(sut.getLastTwoBooks(bookList))
                .contains(bookList.get(4))
                .contains(bookList.get(5));
    }

    @Test
    public void getLastTwoBooksStream() {
        assertThat(sut.getLastTwoBooksStream(bookList))
                .contains(bookList.get(4))
                .contains(bookList.get(5));
    }

    @Test
    public void findOldestBook() {
        assertEquals(bookList.get(1), sut.findOldestBook(bookList));
    }

    @Test
    public void findOldestStream() {
        assertEquals(bookList.get(1), sut.findOldestStream(bookList));
    }

    @Test
    public void findYoungestBook() {
        assertEquals(bookList.get(4), sut.findYoungestBook(bookList));
    }

    @Test
    public void findYoungestBookStream() {
        assertEquals(bookList.get(4), sut.findYoungestBookStream(bookList));
    }

    @Test
    public void sumOfYears() {
        assertEquals(12026, sut.sumOfYears(bookList));
    }

    @Test
    public void sumOfYearsStream() {
        assertEquals(12026, sut.sumOfYearsStream(bookList));
    }

    @Test
    public void howManyBooksAfter2007() {
        assertEquals(2, sut.howManyBooksAfter2007(bookList));
    }

    @Test
    public void howManyBooksAfter2007Stream() {
        assertEquals(2, sut.howManyBooksAfter2007Stream(bookList));
    }

    @Test
    public void isAllBooksAreNewerThan2000() {
        assertFalse(sut.isAllBooksAreNewerThan2000(bookList));
    }

    @Test
    public void isAllBooksAreNewerThan2000Stream() {
        assertFalse(sut.isAllBooksAreNewerThan2000Stream(bookList));
    }

    @Test
    public void averageOfBookYears() {
        //2004,(3)
        assertThat(sut.averageOfBookYears(bookList))
                .isCloseTo(2004.3, Assertions.offset(0.1d));
    }

    @Test
    public void averageOfBookYearsStream() {
        assertThat(sut.averageOfBookYearsStream(bookList))
                .isCloseTo(2004.3, Assertions.offset(0.1d));
    }

    @Test
    public void isThereAnyBookBefore2003() {
        assertTrue(sut.isThereAnyBookBefore2003(bookList));
    }

    @Test
    public void isThereAnyBookBefore2003Stream() {
        assertTrue(sut.isThereAnyBookBefore2003Stream(bookList));
    }


    @Test
    public void getBooksWithTitleStartingWithCAndYoungerThan2007() {
        assertThat(sut.getBooksWithTitleStartingWithCAndYoungerThan2007(bookList)).hasSize(0);
    }

    @Test
    public void sortBooksByYearDescending() {
        sut.sortBooksByYearDescending(bookList);
    }

    @Test
    public void get3Lists() {
        System.out.println(sut.get3Lists(bookList));
        assertThat(sut.get3Lists(bookList)).hasSize(3);
    }

    @Test
    public void getYearBookMap() {
        System.out.println(sut.getYearBookMap(bookList));
    }

    @Test
    public void getBooksAfter2009Map() {
        System.out.println(sut.getBooksAfter2009Map(bookList));
    }

    @Test
    public void add100YearsToEveryBook() {
        assertThat(sut.add100YearsToEveryBook(bookList.subList(0,2))).extracting("year").contains(2103,2098);

    }
}