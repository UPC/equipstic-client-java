package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.TipusUs;

public interface TipusUsDao {

    List<TipusUs> getTipusUs();

    List<TipusUs> getTipusUsByUnitat(long idUnitat);

    TipusUs getTipusUsById(long idTipusUs);

}
