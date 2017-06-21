package models.j2;

public class NewsContent {
    private int id;
    private String Content;
    private String summary;
    private String keywords;
    private String rawText;

    public NewsContent() {
    }

    public int getId() {
        return id;
    }

    public NewsContent addId(int id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return Content;
    }

    public NewsContent addContent(String content) {
        Content = content;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public NewsContent addSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public NewsContent addKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getRawText() {
        return rawText;
    }

    public NewsContent addRawText(String rawText) {
        this.rawText = rawText;
        return this;
    }
}
