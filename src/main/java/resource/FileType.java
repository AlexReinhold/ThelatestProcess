package resource;

public enum FileType {

    VIDEO(1, "VIDEO"),
    IMAGEN(2, "IMAGEN");

    private int id;
    private String type;

    FileType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
