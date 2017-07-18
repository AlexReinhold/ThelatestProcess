package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    static final String HELP = "-help", CATEGORY = "-category:", ALL = "-all";

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.setProperty("current.date", sdf.format(date));
        BeginProcess beginProcess = new BeginProcess();
        beginProcess.start();

    }

    private void processArgs(String[] args){

        if(args.length==0){
            System.out.println("Sync all categories");
        }else
        if(args.length==1){
            switch (args[0]){
                case HELP:

                break;
                case CATEGORY:

                break;
                case ALL:

                break;
                default:

                break;
            }
        }else{

        }

    }


}   