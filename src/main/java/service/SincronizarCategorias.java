package service;

import dao.CategoriaDao;
import models.tn.Categoria;
import models.ttrss.FeedCategories;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

import util.ComparadorDeListas;

/**
 * Created by nlperez on 1/31/17.
 */
public class SincronizarCategorias implements Runnable {

    private String pais;
    private CategoriaDao categoriaDao;
    private ComparadorDeListas comparador;
    private Logger logger;

    public SincronizarCategorias(DataSource tnDaTaSource, DataSource a3DataSource, DataSource ttrssDateSource, String pais) {
        categoriaDao = new CategoriaDao(tnDaTaSource, a3DataSource, ttrssDateSource);
        comparador = new ComparadorDeListas();
        this.pais = pais;

    }

    public void run(){
        long inicio = System.currentTimeMillis();
        logger = Logger.getLogger(Thread.currentThread().getName());
        List<FeedCategories> categoriasPadreTtrss;
        List<Categoria> categoriasPadreTN;
        pais = Character.toUpperCase(pais.charAt(0)) + pais.substring(1);

        if (!pais.equals("")) {
            categoriasPadreTtrss = categoriaDao.categoriasPadreTtrss(pais); // POR LOS MOMENTOS EL PAIS SIEMPRE ES VZLA
        } else {
            categoriasPadreTtrss = categoriaDao.categoriasDeTtrss();
        }
        if (categoriasPadreTtrss.size() > 0) {
            categoriasPadreTN = categoriaDao.categoriasPadreTN();
            HashMap<String, List<Categoria>> diferencias = comparador.CompararCategorias(categoriasPadreTtrss, categoriasPadreTN);
            if (diferencias.get("forUpdate").size() == 0
                    && diferencias.get("forInsert").size() == 0) {
                logger.info("Las categorias se encuentran sincronizadas actualmente");
                logger.info("-------------------------------------------------------");
            } else if (diferencias.get("forInsert").size() > 0) {
                for (Categoria categoria : diferencias.get("forInsert")) {
                    try {
                        categoriaDao.nuevaCategoriaPadreTN(categoria);
                        logger.info("Nueva categoria " + categoria.getNombre() + " en TN");
                    } catch (DuplicateKeyException e) {
                        logger.warn("La categoria: " + categoria.getNombre() + " <- ya existe en TN");
                    }
                }
            }
            if (diferencias.get("forUpdate").size() > 0) {
                for (Categoria categoria : diferencias.get("forUpdate")) {
                    categoriaDao.actualizarCategoriaTN(categoria);
                    logger.info("categoria actualizada " + categoria.getNombre() + " en TN");
                    }
            }
            long fin = System.currentTimeMillis();
            logger.info("------- Categorias Sincronizadas --------------------------");
            logger.info("N° de Categorias insertadas: " + diferencias.get("forInsert").size());
            logger.info("N° de Categorias modificadas: " + diferencias.get("forUpdate").size());
            logger.info("Categorias sincronizadas en " + (fin - inicio) + " ms");
            logger.info("-----------------------------------------------------------");
            SincronizarSubCategoriasConTN(pais);
        } else {
            logger.error("Alguna de las listas de categorias no retorno ningun valor revisen esa verga.");
        }

    }

    private void SincronizarSubCategoriasConTN(String pais) {
        long inicio = System.currentTimeMillis();
        logger.info("Sincronizando SubCategorias con TN");
        List<FeedCategories> subCategoriasTtrss = categoriaDao.subCategoriasTtrss();
        List<Categoria> subCategoriasTN = categoriaDao.subCategoriasTN();
        HashMap<String, List<Categoria>> diferencias;


        if (subCategoriasTtrss.size() > 0) {
            diferencias = comparador.CompararSubCategorias(subCategoriasTtrss, subCategoriasTN);
            if (diferencias.get("forInsert").size() == 0 && diferencias.get("forUpdate").size() == 0) {
                logger.info("Las subcategorias Se encuentras actualmente Sincronizadas");
            }
            if (diferencias.get("forInsert").size() > 0) {
                for (Categoria subCat : diferencias.get("forInsert")) {
                    FeedCategories fc = categoriaDao.categoriaPorIdTtrss(subCat.getCategoriaPadre());
                    Categoria catPadre = categoriaDao.categoriaPorSlugTN(fc.getSlug());
                    subCat.addCategoriaPadre(catPadre.getId())
                            .addSlug(subCat.getSlug() + "-" + catPadre.getSlug());

                    try {
                        if (categoriaDao.nuevaSubCategoriaTN(subCat) > 0) {
                            logger.info("Nueva Sub-Categoria Insertada: " + subCat.getNombre() + " correspondiente a la categoria: "
                                    + catPadre.getNombre() + " en TN");
                        }
                    } catch (DuplicateKeyException e) {
                        logger.warn("La categoria: " + subCat.getNombre() + " ya existe en TN");
                    }

                }
            }
            if (diferencias.get("forUpdate").size() > 0) {
                for (Categoria subCat : diferencias.get("forUpdate")) {

                    if (categoriaDao.actualizarCategoriaTN(subCat) > 0) {
                        System.out.println("Categoria Actualizada: " + subCat.getNombre() + " en TN");
                    }
                }
            }
            long fin = System.currentTimeMillis();
            logger.info("SubCategorias Insertadas: " + diferencias.get("forInsert").size());
            logger.info("Subcategorias Actualizadas: " + diferencias.get("forUpdate").size());
            logger.info("SubCategorias Sincronizadas en " + (fin - inicio) + " ms");
            logger.info("-----------------------------------------------------------");
        }
    }

}
