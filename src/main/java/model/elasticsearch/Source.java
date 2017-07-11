package model.elasticsearch;

public class Source {
    private int id;
    private String name;

    public Source() {
    }

    public int getId() {
        return id;
    }

    public Source addId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Source addName(String name) {
        this.name = name;
        return this;
    }
}
