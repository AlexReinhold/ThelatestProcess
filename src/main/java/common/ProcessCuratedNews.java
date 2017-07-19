package common;

import model.j2.CuratedNew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessCuratedNews {

    List<CuratedNew> J2News;
    private List<Integer> completed;

    public ProcessCuratedNews(List<CuratedNew> J2News) {
        this.J2News = J2News;
        this.completed = Collections.synchronizedList( new ArrayList<>() );
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

    public synchronized void addCompleted(int id){
        completed.add(id);
    }

    public List<Integer> getCompleted(){
        return completed;
    }

}
