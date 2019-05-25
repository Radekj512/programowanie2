package library.utils;

import library.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Validator {

    // book validator

    private static boolean validateTitle(String title) {
        return title.length() >= 4;
    }

    private static boolean validateIbsn(String ibsn) {
        return ibsn.length() == 10;
    }

    private static boolean validateYear(int year) {
        return year > 800 && year <= Calendar.getInstance().get(Calendar.YEAR);
    }

    private static boolean validateBindingType(String binding) {
        return binding.equalsIgnoreCase("t") || binding.equalsIgnoreCase("m");
    }

    private static boolean valideateAuthorsIds(String ids) {
        String[] tmp = ids.split(",");
        List<Integer> tmpIds = new ArrayList<>();

        List<Integer> authorsId = new LoadAuthors().getAuthorsIds();

        if (ids.length() > 0) {
            for (String id : tmp) {
                tmpIds.add(Integer.parseInt(id));
            }
           /* for (int i = 0; i < tmp.length; i++) {
                tmpIds.add(Integer.parseInt(tmp[i]));
            }*/
            return tmpIds.stream().filter(authorsId::contains).count() == tmpIds.size();
        } else {
            return false;
        }
    }

    public static boolean validateBook(String title, String ibsn, int year, String binding, String ids) {
        if (validateYear(year) && validateBindingType(binding)
                && validateIbsn(ibsn) && validateTitle(title) && valideateAuthorsIds(ids)) {
            return true;
        } else {
            return false;
        }

    }
    public static boolean validateBook(Book book){
        if (validateYear(book.getYear()) && validateBindingType(book.getBinding())
                && validateIbsn(book.getIbsn()) && validateTitle(book.getTitle()) && valideateAuthorsIds(book.getAuthorsIds())) {
            return true;
        } else {
            return false;
        }
    }

}
