package common;

import model.j2.CuratedNew;
import java.util.List;

public class ProcessCuratedNews {

    List<CuratedNew> J2News;

    public ProcessCuratedNews(List<CuratedNew> J2News) {
        this.J2News = J2News;
    }

    public synchronized CuratedNew obtenerCuratedNew() {
        while (J2News.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
//                System.err.println(ex);
                ex.printStackTrace();
            }
        }
        notify();
        return J2News.remove(0);
    }
    public boolean isFinish() {
        return !J2News.isEmpty();
    }

}
