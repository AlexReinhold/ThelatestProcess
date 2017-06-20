package common;

import models.tn.ArticuloNoProcesado;

import java.util.List;

/**
 * Created by nlperez on 4/25/17.
 */
public class ProcesarArticulo {

    List<ArticuloNoProcesado> articuloNoProcesados;

    public ProcesarArticulo(List<ArticuloNoProcesado> articuloNoProcesados) {
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
