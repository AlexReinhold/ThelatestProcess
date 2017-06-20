package common;

import models.j2.CuratedNew;
import java.util.List;

public class ProcessCuratedNews {

    List<CuratedNew> articulosDeA3;

    public ProcessCuratedNews(List<CuratedNew> articulosDeA3) {
        this.articulosDeA3 = articulosDeA3;
    }

    public synchronized CuratedNew obtenerCuratedNew() {
        while (articulosDeA3.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
        notify();
        return articulosDeA3.remove(0);
    }
    public boolean isFinish() {
        return !articulosDeA3.isEmpty();
    }

}
