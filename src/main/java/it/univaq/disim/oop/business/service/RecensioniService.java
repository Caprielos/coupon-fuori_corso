package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Recensione;

public interface RecensioniService {

    void creaRecensione(Recensione recensione) throws BusinessException;


}
