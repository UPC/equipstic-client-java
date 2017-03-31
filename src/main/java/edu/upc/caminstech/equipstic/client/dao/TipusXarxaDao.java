package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.TipusXarxa;

public interface TipusXarxaDao {

    List<TipusXarxa> getTipusXarxa();

    TipusXarxa getTipusXarxaById(long idTipusXarxa);

}
