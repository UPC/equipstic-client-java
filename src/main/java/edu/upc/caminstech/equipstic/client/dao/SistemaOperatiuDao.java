package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.SistemaOperatiu;

public interface SistemaOperatiuDao {

    List<SistemaOperatiu> getSistemesOperatius();

    List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria);

    List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi);

    List<SistemaOperatiu> getSistemesOperatiusByNom(String nom);

    SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu);

}
