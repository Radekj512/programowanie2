public class Book {
    private String title;
    private String ibsn;
    private int year;

    public Book(String title, String ibsn, int year) {
        this.title = title;
        this.ibsn = ibsn;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getIbsn() {
        return ibsn;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return ""+title + " " + ibsn + " " + year;
    }
}
