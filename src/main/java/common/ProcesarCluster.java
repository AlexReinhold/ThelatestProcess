package common;

import models.a3.Cluster;
import java.util.List;

/**
 * Created by nlperez on 2/1/17.
 */
public class ProcesarCluster {

    List<Cluster> noticiasDeA3;

    public ProcesarCluster(List<Cluster> noticiasDeA3) {
        this.noticiasDeA3 = noticiasDeA3;
    }

    public synchronized Cluster obtenerCluster() {
        while (noticiasDeA3.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
        notify();
        return noticiasDeA3.remove(0);
    }

    public boolean isFinish() {
        return !noticiasDeA3.isEmpty();
    }

}
