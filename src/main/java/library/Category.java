package library;

public class Category {
private  int id;
private String name;
private int priority;

    public Category(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "" + id + ". "+name;
    }
}
