package model.tl;

public class Source {
    private int id;
    private String name;
    private String section;
    private String externalId;
    private String alias;
    private String sourceFeedUrl;
    private String url;
    private String iconUrl;

    public Source() {
    }

    public Source(int id, String name, String url, String externalId, String iconUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.externalId = externalId;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public Source addId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Source addName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Source addUrl(String url) {
        this.url = url;
        return this;
    }

    public String getExternalId() {
        return externalId;
    }

    public Source addExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Source addIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getSection() {
        return section;
    }

    public Source addSection(String section) {
        this.section = section;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public Source addAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getSourceFeedUrl() {
        return sourceFeedUrl;
    }

    public Source addSourceFeedUrl(String sourceFeedUrl) {
        this.sourceFeedUrl = sourceFeedUrl;
        return this;
    }
}