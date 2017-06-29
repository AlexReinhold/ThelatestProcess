package model.elasticsearch;

import java.sql.Timestamp;

public class Story {

    private int id;
    private String name;
    private String slug;
    private int position;
    private Timestamp deadline;
    private Category category;
    private int newsCount;
    private int score;
    private int sourcesCount;
    private int sourcesString;
    private Timestamp pubDateFromMostRecentNews;
    private RecentNews mostRecentNews;

    public Story() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public int getPosition() {
        return position;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public Category getCategory() {
        return category;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public int getScore() {
        return score;
    }

    public int getSourcesCount() {
        return sourcesCount;
    }

    public int getSourcesString() {
        return sourcesString;
    }

    public Timestamp getPubDateFromMostRecentNews() {
        return pubDateFromMostRecentNews;
    }

    public RecentNews getMostRecentNews() {
        return mostRecentNews;
    }

    public Story addId(int id) {
        this.id = id;
        return this;
    }

    public Story addName(String name) {
        this.name = name;
        return this;
    }

    public Story addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Story addPosition(int position) {
        this.position = position;
        return this;
    }

    public Story addDeadline(Timestamp deadline) {
        this.deadline = deadline;
        return this;
    }

    public Story addCategory(Category category) {
        this.category = category;
        return this;
    }

    public Story addNewsCount(int newsCount) {
        this.newsCount = newsCount;
        return this;
    }

    public Story addScore(int score) {
        this.score = score;
        return this;
    }

    public Story addSourcesCount(int sourcesCount) {
        this.sourcesCount = sourcesCount;
        return this;
    }

    public Story addSourcesString(int sourcesString) {
        this.sourcesString = sourcesString;
        return this;
    }

    public Story addPubDateFromMostRecentNews(Timestamp pubDateFromMostRecentNews) {
        this.pubDateFromMostRecentNews = pubDateFromMostRecentNews;
        return this;
    }

    public Story addMostRecentNews(RecentNews mostRecentNews) {
        this.mostRecentNews = mostRecentNews;
        return this;
    }

    public class RecentNews {
        private int id;
        private String title;
        private String snippet;
        private String imgUrl;
        private Timestamp publicationDate;
        private Source source;

        public RecentNews() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSnippet() {
            return snippet;
        }

        public void setSnippet(String snippet) {
            this.snippet = snippet;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Timestamp getPublicationDate() {
            return publicationDate;
        }

        public void setPublicationDate(Timestamp publicationDate) {
            this.publicationDate = publicationDate;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }
    }

}
