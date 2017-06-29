package model.tl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Story {
    private int id;
    private Category category;
    private String name;
    private int externalId;
    private String slug;
    private int views;
    private Timestamp deadLine;
    private int position;

    //New items
    private int shares;
    private String tags;
    private Timestamp pubDate;
    private Timestamp lastUpdate;
    private int sourcesNumber;
    private List<News> news;

    public Story() {
        news = new ArrayList<News>();
    }

    public int getId() {
        return id;
    }

    public Story addId(int id) {
        this.id = id;
        return this;
    }

    public int getViews() {
        return views;
    }

    public Story addViews(int views) {
        this.views = views;
        return this;
    }

    public int getShares() {
        return shares;
    }

    public Story addShares(int shares) {
        this.shares = shares;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Story addCategory(Category category) {
        this.category = category;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public Story addPosition(int position) {
        this.position = position;
        return this;
    }

    public String getName() {
        return name;
    }

    public Story addName(String name) {
        this.name = name;
        return this;
    }

    public int getExternalId() {
        return externalId;
    }

    public Story addExternalId(int externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Story addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public Story addDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Story addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public Story addPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public Story addLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public int getSourcesNumber() {
        return sourcesNumber;
    }

    public Story addSourcesNumber(int sourcesNumber) {
        this.sourcesNumber = sourcesNumber;
        return this;
    }

    public List<News> getNews() {
        return news;
    }

    public Story addNews(List<News> news) {
        this.news = news;
        return this;
    }

}

