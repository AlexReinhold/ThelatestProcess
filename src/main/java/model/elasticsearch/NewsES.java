package model.elasticsearch;

import java.sql.Timestamp;

public class NewsES {

    private int id;
    private String title;
    private Timestamp publicationDate;
    private String snippet;
    private String imgUrl;
    private String url;
    protected Source source;
    protected Story story;

    public NewsES() {
    }

    public int getId() {
        return id;
    }

    public NewsES addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsES addTitle(String title) {
        this.title = title;
        return this;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public NewsES addPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public NewsES addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public NewsES addImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public NewsES addUrl(String url) {
        this.url = url;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public NewsES addSource(Source source) {
        this.source = source;
        return this;
    }

    public Story getStory() {
        return story;
    }

    public NewsES addStory(Story story) {
        this.story = story;
        return this;
    }

    public class Story {

        private int id;
        private String slug;
        private String name;
        private Category category;

        public Story() {
        }

        public int getId() {
            return id;
        }

        public Story addId(int id) {
            this.id = id;
            return this;
        }

        public String getSlug() {
            return slug;
        }

        public Story addSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public String getName() {
            return name;
        }

        public Story addName(String name) {
            this.name = name;
            return this;
        }

        public Category getCategory() {
            return category;
        }

        public Story addCategory(Category category) {
            this.category = category;
            return this;
        }

    }

}

