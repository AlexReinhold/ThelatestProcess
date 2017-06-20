package models.j2;

/**
 * Created by nlperez on 1/25/17.
 */
public class Cluster {
    private int id;
    private int size;
    private String slug;
    private String tittle;
    private int mainCatId;
    private int subCatId;
    private boolean synchronizeD;
    private boolean sound;

    public Cluster() {
    }

    public int getId() {
        return id;
    }

    public Cluster addId(int id) {
        this.id = id;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Cluster addSize(int size) {
        this.size = size;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Cluster addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public Cluster addTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public int getMainCatId() {
        return mainCatId;
    }

    public Cluster addMainCatId(int mainCatId) {
        this.mainCatId = mainCatId;
        return this;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public Cluster addSubCatId(int subCatId) {
        this.subCatId = subCatId;
        return this;
    }

    public boolean getSynchronizeD() {
        return synchronizeD;
    }

    public Cluster addSynchronizeD(boolean synchronizeD) {
        this.synchronizeD = synchronizeD;
        return this;
    }

    public boolean getSound() {
        return sound;
    }

    public Cluster addSound(boolean sound) {
        this.sound = sound;
        return this;
    }
}
