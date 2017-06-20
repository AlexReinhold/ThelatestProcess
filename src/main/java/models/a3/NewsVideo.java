package models.a3;

/**
 * Created by nlperez on 4/21/17.
 */
public class NewsVideo {
    private int id;
    private String content;
    private String originalContent;

    public NewsVideo() {
    }

    public int getId() {
        return id;
    }

    public NewsVideo addId(int id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsVideo addContent(String content) {
        this.content = content;
        return this;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public NewsVideo addOriginalContent(String originalContent) {
        this.originalContent = originalContent;
        return this;
    }
}
