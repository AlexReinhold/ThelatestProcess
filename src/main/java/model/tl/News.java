package model.tl;

import java.sql.Timestamp;

public class News {
    private int id;
    private Story story;
    private Source source;
    private String url;
    private String externalId;
    private String title;
    private String snippet;
    private String imgUrl;
    private String author;
    private int score;
    private Timestamp pubDate;
    private Timestamp addedDate;
    private boolean staffPicks;

    //New items
    private String content;
    private String tags;

    public News() {
    }

    public int getId() {
        return id;
    }

    public News addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public News addTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public News addContent(String content) {
        this.content = content;
        return this;
    }

    public Story getStory() {
        return story;
    }

    public News addStory(Story story) {
        this.story = story;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public News addAuthor(String author) {
        this.author = author;
        return this;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public News addPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public News addAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public News addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public News addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public News addUrl(String url) {
        this.url = url;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public News addSource(Source source) {
        this.source = source;
        return this;
    }

    public String getExternalId() {
        return externalId;
    }

    public News addExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public News addImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public int getScore() {
        return score;
    }

    public News addScore(int score) {
        this.score = score;
        return this;
    }

    public boolean isStaffPicks() {
        return staffPicks;
    }

    public News addStaffPicks(boolean staffPicks) {
        this.staffPicks = staffPicks;
        return this;
    }

}