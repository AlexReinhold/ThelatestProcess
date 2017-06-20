package models.ttrss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nlperez on 1/12/17.
 */
public class FeedCategories {
    private int id;
    private int owner_uid;
    private boolean collapsed;
    private int orderId;
    private String viewSettings;
    private int parentCat;
    private String title;
    private String slug;
    private List<FeedCategories> subCategories;

    public FeedCategories() {
        subCategories = new ArrayList<FeedCategories>();
    }

    public FeedCategories(int id, int owner_uid, boolean collapsed, int orderId, String viewSettings, int parentCat, String title,
                          List<FeedCategories> subCategories) {
        this.id = id;
        this.owner_uid = owner_uid;
        this.collapsed = collapsed;
        this.orderId = orderId;
        this.viewSettings = viewSettings;
        this.parentCat = parentCat;
        this.title = title;
        this.subCategories = subCategories;
    }

    public int getId() {
        return id;
    }

    public FeedCategories addId(int id) {
        this.id = id;
        return this;
    }

    public int getOwner_uid() {
        return owner_uid;
    }

    public FeedCategories addOwner_uid(int owner_uid) {
        this.owner_uid = owner_uid;
        return this;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public FeedCategories addCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public FeedCategories addOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getViewaddtings() {
        return viewSettings;
    }

    public FeedCategories addViewSettings(String viewaddtings) {
        this.viewSettings = viewaddtings;
        return this;
    }

    public int getParentCat() {
        return parentCat;
    }

    public FeedCategories addParentCat(int parentCat) {
        this.parentCat = parentCat;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FeedCategories addTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSlug() {
        return title.replaceAll(" ", "-").toLowerCase();
    }

    public List<FeedCategories> getSubCategories() {
        return subCategories;
    }

    public FeedCategories addSubCategories(List<FeedCategories> subCategories) {
        this.subCategories = subCategories;
        return this;
    }
}