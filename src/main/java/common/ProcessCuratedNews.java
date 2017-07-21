package common;

import model.j2.CuratedNews;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessCuratedNews {

    private List<CuratedNews> J2News = new LinkedList<>();
    private List<Integer> completed = new LinkedList<>();

    public ProcessCuratedNews(List<CuratedNews> J2News) {
        this.J2News = Collections.synchronizedList(J2News);
        this.completed = Collections.synchronizedList( new ArrayList<>() );
    }

    public synchronized Optional<CuratedNews> getCuratedNews() {

//        while (J2News.isEmpty()) {
//            try {
//                System.out.println("wait");
//                wait();
//                System.out.println("done");
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }

        if(J2News.isEmpty())
            return Optional.empty();

        return Optional.of(J2News.remove(0));

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
