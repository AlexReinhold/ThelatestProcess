package common;

import models.a3.CuratedNew;
import java.util.List;

/**
 * Created by nlperez on 2/1/17.
 */
public class ProcesarCuratedNew {

    List<CuratedNew> articulosDeA3;

    public ProcesarCuratedNew(List<CuratedNew> articulosDeA3) {
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
