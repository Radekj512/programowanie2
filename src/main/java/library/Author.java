package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {
    private  int id;
    private String name;
    private int age;

    public Author(String name) {
        this.name = name;
    }

    public Author(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAgeAndId(List<Author> listToSet, List<Author> authorsList){
        listToSet.forEach(author -> author.setIdAndAge(authorsList.get(authorsList.indexOf(author)).getId(), authorsList.get(authorsList.indexOf(author)).getAge() ));
    }
    private void setIdAndAge(int id, int age){
        setId(id);
        setAge(age);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
