package model.j2;

import model.ttrss.Source;

import java.sql.Timestamp;

public class CuratedNew {
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

    public CuratedNew() {
    }

    public int getId() {
        return id;
    }

    public CuratedNew addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CuratedNew addTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink() {
        return link;
    }

    public CuratedNew addLink(String link) {
        this.link = link;
        return this;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public CuratedNew addPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public CuratedNew addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public Timestamp getDateEntered() {
        return dateEntered;
    }

    public CuratedNew addDateEntered(Timestamp dateEntered) {
        this.dateEntered = dateEntered;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CuratedNew addAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public CuratedNew addCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public CuratedNew addSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public CuratedNew addSource(Source source) {
        this.source = source;
        return this;
    }

    public boolean isClustered() {
        return clustered;
    }

    public CuratedNew addClustered(boolean clustered) {
        this.clustered = clustered;
        return this;
    }

    public boolean getSynchronizeD() {
        return synchronizeD;
    }

    public CuratedNew addSynchronizeD(boolean synchronizeD) {
        this.synchronizeD = synchronizeD;
        return this;
    }

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public CuratedNew addNewsContent(NewsContent newsContent) {
        this.newsContent = newsContent;
        return this;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public CuratedNew addCluster(Cluster cluster) {
        this.cluster = cluster;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CuratedNew addImage(String image) {
        this.image = image;
        return this;
    }

}
