package model.elasticsearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class StoryES {

    private int id;
    private String name;
    private String slug;
    private int position;
    private Timestamp deadline;
    private Category category;
    private int newsCount;
    private BigDecimal score;
    private int sourcesCount;
    private String sourcesString;
    private Timestamp pubDateFromMostRecentNews;
    private RecentNews mostRecentNews;

    public StoryES() {
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

    public BigDecimal getScore() {
        return score;
    }

    public int getSourcesCount() {
        return sourcesCount;
    }

    public String getSourcesString() {
        return sourcesString;
    }

    public Timestamp getPubDateFromMostRecentNews() {
        return pubDateFromMostRecentNews;
    }

    public RecentNews getMostRecentNews() {
        return mostRecentNews;
    }

    public StoryES addId(int id) {
        this.id = id;
        return this;
    }

    public StoryES addName(String name) {
        this.name = name;
        return this;
    }

    public StoryES addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public StoryES addPosition(int position) {
        this.position = position;
        return this;
    }

    public StoryES addDeadline(Timestamp deadline) {
        this.deadline = deadline;
        return this;
    }

    public StoryES addCategory(Category category) {
        this.category = category;
        return this;
    }

    public StoryES addNewsCount(int newsCount) {
        this.newsCount = newsCount;
        return this;
    }

    public StoryES addScore(int views, int wtms) {

        int sources = getSourcesCount();
        int news = getNewsCount();

        this.score = calculate(views, sources, wtms, news);

        return this;
    }

    public StoryES addSourcesCount(int sourcesCount) {
        this.sourcesCount = sourcesCount;
        return this;
    }

    public StoryES addSourcesString(String sourcesString) {
        this.sourcesString = sourcesString;
        return this;
    }

    public StoryES addPubDateFromMostRecentNews(Timestamp pubDateFromMostRecentNews) {
        this.pubDateFromMostRecentNews = pubDateFromMostRecentNews;
        return this;
    }

    public StoryES addMostRecentNews(RecentNews mostRecentNews) {
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

        public RecentNews addId(int id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public RecentNews addTitle(String title) {
            this.title = title;
            return this;
        }

        public String getSnippet() {
            return snippet;
        }

        public RecentNews addSnippet(String snippet) {
            this.snippet = snippet;
            return this;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public RecentNews addImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Timestamp getPublicationDate() {
            return publicationDate;
        }

        public RecentNews addPublicationDate(Timestamp publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Source getSource() {
            return source;
        }

        public RecentNews addSource(Source source) {
            this.source = source;
            return this;
        }
    }

    private static BigDecimal calculate(int views, int sources, int wtms, int news){

        if(news == 0)
            return BigDecimal.ZERO;
        else {
            double r1 = 0.6*sources;
            double r2 = 0.2*((double) sources/ (double) news);
            double r3 = 0.1*views;
            double r4 = 0.3*wtms;
            return new BigDecimal(r1 + r2 + r3 + r4).setScale(2, RoundingMode.HALF_UP);
        }
    }

}
