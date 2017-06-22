package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.setProperty("current.date", sdf.format(date));
        BeginProcess beginProcess = new BeginProcess();
        beginProcess.start();


    }
}   