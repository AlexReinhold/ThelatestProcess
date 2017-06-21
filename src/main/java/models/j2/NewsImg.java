package models.j2;

public class NewsImg {
    private int id;
    private String content;
    private String originalContent;
    private int width;
    private int height;

    public int getId() {
        return id;
    }

    public NewsImg addId(int id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsImg addContent(String content) {
        this.content = content;
        return this;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public NewsImg addOriginalContent(String originalContent) {
        this.originalContent = originalContent;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public NewsImg addWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public NewsImg addHeight(int height) {
        this.height = height;
        return this;
    }
}
