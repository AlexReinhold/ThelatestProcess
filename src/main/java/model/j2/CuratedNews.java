package model.j2;

import model.ttrss.Source;

import java.sql.Timestamp;

public class CuratedNews {
    private int id;
    private String title;
    private String link;
    private Timestamp pubDate;
    private String snippet;
    private Timestamp dateEntered;
    private String author;
    private int categoryId;
    private int subCategoryId;
    private Source source;
    private boolean clustered;
    private boolean synchronizeD;
    private NewsContent newsContent;
    private Cluster cluster;
    private String image;

    public CuratedNews() {
    }

    public int getId() {
        return id;
    }

    public CuratedNews addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CuratedNews addTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink() {
        return link;
    }

    public CuratedNews addLink(String link) {
        this.link = link;
        return this;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public CuratedNews addPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public CuratedNews addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public Timestamp getDateEntered() {
        return dateEntered;
    }

    public CuratedNews addDateEntered(Timestamp dateEntered) {
        this.dateEntered = dateEntered;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CuratedNews addAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public CuratedNews addCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public CuratedNews addSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public CuratedNews addSource(Source source) {
        this.source = source;
        return this;
    }

    public boolean isClustered() {
        return clustered;
    }

    public CuratedNews addClustered(boolean clustered) {
        this.clustered = clustered;
        return this;
    }

    public boolean getSynchronizeD() {
        return synchronizeD;
    }

    public CuratedNews addSynchronizeD(boolean synchronizeD) {
        this.synchronizeD = synchronizeD;
        return this;
    }

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public CuratedNews addNewsContent(NewsContent newsContent) {
        this.newsContent = newsContent;
        return this;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public CuratedNews addCluster(Cluster cluster) {
        this.cluster = cluster;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CuratedNews addImage(String image) {
        this.image = image;
        return this;
    }

}
