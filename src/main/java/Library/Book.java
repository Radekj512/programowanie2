package library;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String ibsn;
    private int year;
    private String binding;
    private List<Author> authors;
    private Category category;


    public Book(int id, String title, String ibsn, int year, String binding, List<Author> authorsIds, Category category) {
        this.id = id;
        this.title = title;
        this.ibsn = ibsn;
        this.year = year;
        this.binding = binding;
        this.authors = authorsIds;
        this.category = category;
    }

    public int getId() {
        return id;
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

    public void setYear(int year){
        this.year = year;
    }

    private String getAuthorsIds(){
        StringBuilder s = new StringBuilder();
        authors.forEach(author -> s.append(author.getId()).append(","));
        return s.toString();
    }

    private String getCategoryId(){
        return String.valueOf(category.getId());
    }

    public String toCsv(){
        return ""+id+";"+title+";"+ibsn+";"+year+";"+binding+";"+getAuthorsIds()+";"+getCategoryId();
    }

    private String printBinding(){
        if (binding.equalsIgnoreCase("T")){
            return "Twarda";
        }else if(binding.equalsIgnoreCase("m")){
            return "Miękka";
        }else{
            return "";
        }
    }
    public String toPrintFormat(){
        StringBuilder s = new StringBuilder();
        s.append("ID: ").append(id).append("\n");
        s.append("Tytuł: ").append(title).append("\n");
        s.append("ibsn: ").append(ibsn).append("\n");
        s.append("Rok: ").append(year).append("\n");
        s.append("Okładka: ").append(printBinding()).append("\n");
        s.append("Autorzy: ").append(authors).append("\n");
        s.append("Kategoria: ").append(category).append("\n\n");

        return s.toString();

       // return "| " +id + "| "+ title + "\t|\t" + ibsn + "\t|\t" + year + "\t|\t" + binding+ "\t|\t" + authors +"\t|\t" + category+ "\t|\n";
    }
    @Override
    public String toString() {
        return ""+title + " " + ibsn + " " + year;
    }
}
