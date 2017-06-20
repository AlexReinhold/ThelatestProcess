package common;

import models.tl.ArticuloNoProcesado;

import java.util.List;

public class ProcessNews {

    List<ArticuloNoProcesado> articuloNoProcesados;

    public ProcessNews(List<ArticuloNoProcesado> articuloNoProcesados) {
        this.articuloNoProcesados = articuloNoProcesados;
    }

    public synchronized ArticuloNoProcesado obtenerArticulo(){
        while(articuloNoProcesados.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        notify();
        return articuloNoProcesados.remove(0);
    }

    public boolean isFinish(){
        return !articuloNoProcesados.isEmpty();
    }

}
