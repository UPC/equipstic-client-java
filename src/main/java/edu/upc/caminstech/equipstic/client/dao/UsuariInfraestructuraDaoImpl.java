package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.UsuariInfraestructura;
import edu.upc.caminstech.equipstic.client.Response;

public class UsuariInfraestructuraDaoImpl extends RestDao implements UsuariInfraestructuraDao {

    public UsuariInfraestructuraDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    public UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura) {
        return get("/usuariInfraestructura/{idUsuariInfraestructura}",
                new ParameterizedTypeReference<Response<UsuariInfraestructura>>() {
                }, idUsuariInfraestructura);
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructura() {
        List<UsuariInfraestructura> result = get("/usuariInfraestructura",
                new ParameterizedTypeReference<Response<List<UsuariInfraestructura>>>() {
                });
        return sorted(result);
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de l'usuari no pot ser null");
        }
        List<UsuariInfraestructura> result = get("/usuariInfraestructura/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<UsuariInfraestructura>>>() {
                }, nom);
        return sorted(result);
    }

}
