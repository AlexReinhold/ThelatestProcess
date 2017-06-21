package models.ttrss;

public class Source {
    private int id;
    private String title;
    private String feedUrl;
    private String iconUrl;
    private String siteUrl;

    public Source() {
    }

    public int getId() {
        return id;
    }

    public Source addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Source addTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public Source addFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Source addIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public Source addSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public String getSlug() {
        return title.replaceAll(" ", "-").toLowerCase();
    }
}