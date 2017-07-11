package model.elasticsearch;

public class Category {

    private int id;
    private String slug;
    private String name;
    private String parent;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Category addId(int id) {
        this.id = id;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Category addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category addName(String name) {
        this.name = name;
        return this;
    }

    public String getParent() {
        return parent;
    }

    public Category addParent(String parent) {
        this.parent = parent;
        return this;
    }

}
