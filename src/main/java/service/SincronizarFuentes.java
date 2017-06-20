package service;

import dao.FuenteDao;
import models.tn.Fuente;
import models.tn.FuentePorArticulo;
import models.ttrss.Source;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import util.ComparadorDeListas;
import util.Utils;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nlperez on 1/31/17.
 */
public class SincronizarFuentes implements Runnable{

    private FuenteDao fuenteDao;
    private ComparadorDeListas comparador;
    private List<FuentePorArticulo> fuentesDeTN;
    private List<Source> fuentesDettrss;

    public SincronizarFuentes(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource) {
        fuenteDao = new FuenteDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        comparador = new ComparadorDeListas();
        fuentesDeTN = fuenteDao.fuentesDesdeTN();
        fuentesDettrss = fuenteDao.fuentesDesdeTtrss();
    }

    public void run(){
        long inicio = System.currentTimeMillis();
        Logger logger = Logger.getLogger(Thread.currentThread().getName());
        int nuevasFuentes = 0;
        if (fuentesDettrss.size() > 0) {
            Utils util = new Utils();
            HashMap<String, List<Fuente>> diferencias = comparador.CompararFuentes(fuentesDettrss, fuentesDeTN);

            if (diferencias.get("forInsert").size() == 0
                    && diferencias.get("forUpdate").size() == 0) {
                logger.info("Las Fuentes se encuentran actualizadas Actualmente");
            } else if (diferencias.get("forInsert").size() > 0) {
                for (Fuente fuente : diferencias.get("forInsert")) {

                    try {
                        fuente.addNombreCorto(util.nombreCorto(fuente.getNombre()));
                        Fuente fn = fuenteDao.nuevaFuenteTN(fuente);
                        if (fn.getId() > 0) {
                            try {
                                fuenteDao.nuevaFuentePorArticuloTN(fn);
                                nuevasFuentes = nuevasFuentes + 1;
                                logger.info("Nueva fuente " + fuente.getNombre() + " <- en TN");
                            } catch (DuplicateKeyException e) {
                                logger.error("Fuente por articulo duplicado en TN");
                                throw new DuplicateKeyException("Fuente por articulo duplicado en TN");
                            }
                        }
                    } catch (DuplicateKeyException e) {
                        logger.warn("La fuente: " + fuente.getNombre() + " <- ya existe en TN");
                    }

                }
            }
            if (diferencias.get("forUpdate").size() > 0) {

                for (Fuente fuente : diferencias.get("forUpdate")) {
                    fuente.addNombreCorto(util.nombreCorto(fuente.getNombre()));
                    fuenteDao.actualizarFuenteTN(fuente);
                    logger.info("fuente actualizada " + fuente.getNombre() + " en TN");
                }
            }
            long fin = System.currentTimeMillis();
            logger.info("-----------------Fuentes sincronizadas------------");
            logger.info("N° de Fuentes insertadas: " + nuevasFuentes);
            logger.info("N° de Fuentes actualizadas: " + diferencias.get("forUpdate").size());
            logger.info("Fuentes Sincronizadas en " + (fin - inicio) + " ms");
            logger.info("---------------------------------------------------");
        }
    }
}
