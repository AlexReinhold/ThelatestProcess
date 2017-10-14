package model.j2;

import java.sql.Timestamp;

/**
 * Created by kodetec on 14/10/17.
 */
public class Unsynchronized {

    private int id;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public Unsynchronized addId(int id) {
        this.id = id;
        return this;
    }

    public Timestamp getDate() {
        return date;
    }

    public Unsynchronized addDate(Timestamp date) {
        this.date = date;
        return this;
    }

}
