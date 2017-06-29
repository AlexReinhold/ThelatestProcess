package common;

import model.j2.Cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessCluster {

    private List<Cluster> J2Stories;
    private List<Integer> completed;

    public ProcessCluster(List<Cluster> J2Stories) {
        this.J2Stories = J2Stories;
        this.completed = Collections.synchronizedList( new ArrayList<>() );
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

    public void addCompleted(int id){
        completed.add(id);
    }

    public List<Integer> getCompleted(){
        return completed;
    }

}
