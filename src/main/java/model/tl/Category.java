package model.tl;

public class Category {
    private int id;
    private String name;
    private int parentId;
    private int externalId;
    private String slug;
    private boolean active;
    private int menuOrder;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Category addId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category addNombre(String name) {
        this.name = name;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public Category addCategoriaPadre(int àrentId) {
        this.parentId = àrentId;
        return this;
    }

    public int getExternalId() {
        return externalId;
    }

    public Category addIdExterno(int externaId) {
        this.externalId = externaId;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Category addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Category addActive(boolean active) {
        this.active = active;
        return this;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public Category addMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
        return this;
    }
}