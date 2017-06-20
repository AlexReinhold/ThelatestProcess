package common;

import models.j2.Cluster;
import java.util.List;

public class ProcessCluster {

    List<Cluster> noticiasDeA3;

    public ProcessCluster(List<Cluster> noticiasDeA3) {
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
