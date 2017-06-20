package util;

import models.tl.Categoria;
import models.tl.Fuente;
import models.tl.FuentePorArticulo;
import models.ttrss.FeedCategories;
import models.ttrss.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComparadorDeListas {

    public HashMap<String, List<Categoria>> CompararCategorias(List<FeedCategories> lista1, List<Categoria> lista2) {
        HashMap<String, List<Categoria>> diferencias = new HashMap<String, List<Categoria>>();
        List<Categoria> categoriasDiferentes = new ArrayList<Categoria>();
        List<Categoria> categoriasNuevas = new ArrayList<Categoria>();

        if (lista2.size() > 0) {

            for (int i = 0; i < lista1.size(); i++) {
                FeedCategories fc = lista1.get(i);
                if (lista2.size() > 0) {
                    for (int j = 0; j < lista2.size(); j++) {
                        Categoria cat = lista2.get(j);
                        if (fc.getSlug().equals(cat.getSlug()) && fc.getTitle().equals(cat.getNombre())) {
                            lista1.remove(fc);
                            lista2.remove(cat);
                            i--;
                            break;
                        } else if (fc.getSlug().equals(cat.getSlug()) && !fc.getTitle().equals(cat.getNombre())) {
                            categoriasDiferentes.add(new Categoria().addId(cat.getId()).addNombre(fc.getTitle()).
                                    addIdExterno(fc.getId()).addSlug(fc.getSlug()));
                            lista1.remove(fc);
                            lista2.remove(cat);
                            i--;
                            break;
                        } else if ((j + 1) == lista2.size()) {
                            categoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).
                                    addIdExterno(fc.getId()).addSlug(fc.getSlug()));
                        }
                    }
                } else {
                    categoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).addIdExterno(fc.getId())
                            .addSlug(fc.getSlug()));
                }
            }
        } else if (lista2.size() == 0) {
            for (FeedCategories fc : lista1) {
                categoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).
                        addIdExterno(fc.getId()).addSlug(fc.getSlug()));
            }
        }
        diferencias.put("forUpdate", categoriasDiferentes);
        diferencias.put("forInsert", categoriasNuevas);
        return diferencias;
    }

    public HashMap<String, List<Categoria>> CompararSubCategorias(List<FeedCategories> lista1, List<Categoria> lista2) {
        HashMap<String, List<Categoria>> diferencias = new HashMap<String, List<Categoria>>();
        List<Categoria> subCategoriasDiferentes = new ArrayList<Categoria>();
        List<Categoria> subCategoriasNuevas = new ArrayList<Categoria>();

        if (lista2.size() > 0) {

            for (int i = 0; i < lista1.size(); i++) {
                FeedCategories fc = lista1.get(i);
                if (lista2.size() > 0) {
                    for (int j = 0; j < lista2.size(); j++) {
                        Categoria cat = lista2.get(j);
                        if (fc.getSlug().equals(cat.getSlug().substring(0, fc.getSlug().length())) && fc.getTitle().equals(cat.getNombre())) {
                            lista1.remove(fc);
                            lista2.remove(cat);
                            i--;
                            break;
                        } else if (fc.getSlug().equals(cat.getSlug().substring(0, fc.getSlug().length())) && !fc.getTitle().equals(cat.getNombre())) {
                            subCategoriasDiferentes.add(new Categoria().addId(cat.getId()).addNombre(fc.getTitle()).
                                    addIdExterno(fc.getId()).addSlug(fc.getSlug()));
                            lista1.remove(fc);
                            lista2.remove(cat);
                            i--;
                            break;
                        } else if ((j + 1) == lista2.size()) {
                            subCategoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).
                                    addIdExterno(fc.getId()).addSlug(fc.getSlug()).addCategoriaPadre(fc.getParentCat()));
                        }
                    }
                } else {
                    subCategoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).
                            addIdExterno(fc.getId()).addSlug(fc.getSlug()).addCategoriaPadre(fc.getParentCat()));
                }
            }
        } else if (lista2.size() == 0) {
            for (FeedCategories fc : lista1) {
                subCategoriasNuevas.add(new Categoria().addNombre(fc.getTitle()).
                        addIdExterno(fc.getId()).addSlug(fc.getSlug()).addCategoriaPadre(fc.getParentCat()));
            }
        }
        diferencias.put("forUpdate", subCategoriasDiferentes);
        diferencias.put("forInsert", subCategoriasNuevas);
        return diferencias;
    }

    public HashMap<String, List<Fuente>> CompararFuentes(List<Source> lista1, List<FuentePorArticulo> lista2) {
        HashMap<String, List<Fuente>> diferencias = new HashMap<String, List<Fuente>>();
        List<Fuente> fuentesDiferentes = new ArrayList<Fuente>();
        List<Fuente> fuentesNuevas = new ArrayList<Fuente>();

        if (lista2.size() > 0) {

            for (int i = 0; i < lista1.size(); i++) {
                Source src = lista1.get(i);
                if (lista2.size() > 0) {
                    for (int j = 0; j < lista2.size(); j++) {
                        FuentePorArticulo fpa = lista2.get(j);
                        Fuente fn = fpa.getFuente();
                        if (src.getSlug().equals(fn.getSlug()) && src.getTitle().equals(fn.getNombre())
                                && src.getSiteUrl().equals(fn.getUrl())
                                && src.getIconUrl().equals(fn.getFavicon())) {
                            lista1.remove(src);
                            lista2.remove(fpa);
                            i--;
                            break;
                        } else if (src.getSlug().equals(fn.getSlug()) && (!src.getSiteUrl().equals(fn.getUrl())
                                || !src.getIconUrl().equals(fn.getFavicon()) || !src.getTitle().equals(fn.getNombre()))) {
                            fuentesDiferentes.add(new Fuente().addIdExterno(src.getId()).addNombre(src.getTitle()).addSlug(fn.getSlug()).
                                    addFavicon(src.getIconUrl()).addUrl(src.getSiteUrl()));
                            lista1.remove(src);
                            lista2.remove(fpa);
                            i--;
                            break;
                        } else if (!src.getSlug().equals(fn.getSlug()) && (j + 1) == lista2.size()) {
                            fuentesNuevas.add(new Fuente().addIdExterno(src.getId()).addNombre(src.getTitle()).addSlug(src.getSlug()).
                                    addUrl(src.getSiteUrl()).addFavicon(src.getIconUrl()));
                        }
                    }
                } else {
                    fuentesNuevas.add(new Fuente().addIdExterno(src.getId()).addNombre(src.getTitle()).addSlug(src.getSlug()).
                            addUrl(src.getSiteUrl()).addFavicon(src.getIconUrl()));
                }

            }
        } else {
            for (Source src : lista1) {
                fuentesNuevas.add(new Fuente().addIdExterno(src.getId()).addNombre(src.getTitle()).addSlug(src.getSlug()).
                        addUrl(src.getSiteUrl()).addFavicon(src.getIconUrl()));
            }
        }
        diferencias.put("forUpdate", fuentesDiferentes);
        diferencias.put("forInsert", fuentesNuevas);
        return diferencias;
    }

}
