package common;

import util.Lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    static final String HELP = "-help", CATEGORY = "-category:", ALL = "-all";

    public static void main(String[] args) {

        Lock l = new Lock();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.setProperty("current.date", sdf.format(date));
            BeginProcess beginProcess = new BeginProcess();
            beginProcess.start();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

}