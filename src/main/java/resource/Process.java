package resource;

public enum Process {

    SOURCES(1, "sources"),
    CATEGORIES(2, "categories"),
    NEWS(3, "news"),
    STORIES(4, "stories");

    private int id;
    private String name;

    Process(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
