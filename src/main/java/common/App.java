package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.setProperty("current.date", sdf.format(date));
        IniciarProceso iniciarProceso = new IniciarProceso();
        iniciarProceso.iniciar();


    }
}   