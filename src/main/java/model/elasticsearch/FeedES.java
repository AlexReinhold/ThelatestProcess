package model.elasticsearch;

import java.sql.Timestamp;

public class FeedES {

    private int id;
    private String title;
    private String snippet;
    private Timestamp publicationDate;
    private String imgUrl;
    private String url;
    protected Source source;
    protected int story;
    private Category category;

    public FeedES() {
    }

    public int getId() {
        return id;
    }

    public FeedES addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FeedES addTitle(String title) {
        this.title = title;
        return this;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public FeedES addPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public FeedES addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public FeedES addImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FeedES addUrl(String url) {
        this.url = url;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public FeedES addSource(Source source) {
        this.source = source;
        return this;
    }

    public int getStory() {
        return story;
    }

    public FeedES addStory(int story) {
        this.story = story;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public FeedES addCategory(Category category) {
        this.category = category;
        return this;
    }
}

