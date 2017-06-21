package common;

import models.j2.Cluster;
import java.util.List;

public class ProcessCluster {

    private List<Cluster> J2Stories;

    public ProcessCluster(List<Cluster> J2Stories) {
        this.J2Stories = J2Stories;
    }

    public synchronized Cluster obtenerCluster() {
        while (J2Stories.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
//                System.err.println(ex);
                ex.printStackTrace();
            }
        }
        notify();
        return J2Stories.remove(0);
    }

    public boolean isFinish() {
        return !J2Stories.isEmpty();
    }

}
