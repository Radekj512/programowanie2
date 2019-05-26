package library.utils;

import library.Book;
import library.User;

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

    public static boolean validateBook(String title, String ibsn, int year, String binding) {
        if (validateYear(year) && validateBindingType(binding)
                && validateIbsn(ibsn) && validateTitle(title)) {
            return true;
        } else {
            return false;
        }

    }
    public static boolean validateBook(Book book){
        if (validateYear(book.getYear()) && validateBindingType(book.getBinding())
                && validateIbsn(book.getIbsn()) && validateTitle(book.getTitle())) {
            return true;
        } else {
            return false;
        }
    }
    // user
    private static boolean validateName(String name){
        return (name.length() > 2 );
    }
    private static boolean validateLastName(String lastName){
        return (lastName.length() > 2 );
    }
    private static boolean validateEmail(String email){
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        return email.matches(emailRegex);
    }
    public static boolean validateUser(User user){
        return (validateName(user.getName()) && validateLastName(user.getLastName()) && validateEmail(user.getEmail()));
    }

}
