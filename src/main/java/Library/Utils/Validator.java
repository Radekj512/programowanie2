package Library.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    // book validator

    private static boolean validateTitle(String title) {
        if (title.length() >= 4) {
            return true;
        } else return false;
    }

    private static boolean validateIbsn(String ibsn) {
        if (ibsn.length() == 10) {
            return true;
        } else return false;
    }

    private static boolean validateYear(int year) {
        if (year > 800 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
            return true;
        } else return false;
    }

    private static boolean validateBindingType(String binding) {
        if (binding.equalsIgnoreCase("t") || binding.equalsIgnoreCase("m")) {
            return true;
        } else return false;
    }

    private static boolean valideateAuthorsIds(String ids) {
        String[] tmp = ids.split(",");
        List<Integer> tmpIds = new ArrayList<>();

        List<Integer> authorsId = new LoadAuthors().getAuthorsIds();

        if (ids.length() > 0) {
            for (int i = 0; i < tmp.length; i++) {
                tmpIds.add(Integer.parseInt(tmp[i]));
            }
            if (tmpIds.stream().filter(authorsId::contains).count() == tmpIds.size()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validateBook(String title, String ibsn, int year, String binding, String ids) {
        if (validateYear(year) && validateBindingType(binding)
                && validateIbsn(ibsn) && validateTitle(title) && valideateAuthorsIds(ids)){
            return true;
        }else{
            return false;
        }

    }

}
