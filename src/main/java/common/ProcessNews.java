package common;

import model.tl.UnprocessedNews;
import java.util.List;

public class ProcessNews {

    List<UnprocessedNews> unprocessedNews;

    public ProcessNews(List<UnprocessedNews> unprocessedNews) {
        this.unprocessedNews = unprocessedNews;
    }

    public synchronized UnprocessedNews getUnprocessedNews(){
        while(unprocessedNews.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        notify();
        return unprocessedNews.remove(0);
    }

    public boolean isFinish(){
        return !unprocessedNews.isEmpty();
    }

}
