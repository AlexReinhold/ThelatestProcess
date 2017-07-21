package common;

import model.j2.Cluster;

import java.util.*;

public class ProcessCluster {

    private List<Cluster> J2Stories;
    private List<Integer> completed;

    public ProcessCluster(List<Cluster> J2Stories) {
        this.J2Stories = Collections.synchronizedList(J2Stories);
        this.completed = Collections.synchronizedList( new ArrayList<>() );
    }

    public synchronized Optional<Cluster> getCluster() {
//        while (J2Stories.isEmpty()) {
//            try {
//                wait();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }

        J2Stories.isEmpty();

        if(J2Stories.isEmpty())
            return Optional.empty();

        return Optional.of(J2Stories.remove(0));
    }

    public boolean isFinish() {
        return !J2Stories.isEmpty();
    }

    public synchronized void addCompleted(int id){
        completed.add(id);
    }

    public List<Integer> getCompleted(){
        return completed;
    }

}
