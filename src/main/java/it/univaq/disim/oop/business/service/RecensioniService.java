package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Recensione;
import it.univaq.disim.oop.domain.Ristorante;

import java.util.List;

public interface RecensioniService {

    void creaRecensione(Recensione recensione) throws BusinessException;

    List<Recensione> cercaRecensioniPerRistorante(Ristorante ristorante) throws BusinessException;

    List<Recensione> cercaRecensioniPerCliente(Cliente cliente) throws BusinessException;


}
