package library;

import library.utils.Validator;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Book {
    private int id;

    @NotEmpty
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
    public Book(){}

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

    public String getAuthorsIds(){
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

    public String printBinding(){
        if (binding.equalsIgnoreCase("T")){
            return "Twarda";
        }else if(binding.equalsIgnoreCase("m")){
            return "Miękka";
        }else{
            return binding;
        }
    }
    public String getBinding(){
       return binding;
    }
    public Category getCategory(){
        return category;
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
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", year=" + year +
                ", binding='" + binding + '\'' +
                ", authors=" + authors +
                ", category=" + category +
                '}';
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setBinding(String binding) {
        this.binding = binding;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public boolean isValid() {
        return (Validator.validateBook(this));
    }

}
