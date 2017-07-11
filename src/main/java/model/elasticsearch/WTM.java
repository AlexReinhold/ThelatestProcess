package model.elasticsearch;

/**
 * Created by kodetec on 26/06/17.
 */
public class WTM {

    private int id;

    public WTM() {
    }

    public int getId() {
        return id;
    }

    public WTM addId(int id) {
        this.id = id;
        return this;
    }
}
