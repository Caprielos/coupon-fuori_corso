package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Utente;

public interface UtenteService {

    Utente authenticate(String email, String password) throws BusinessException;

    void recordUser(Utente utente) throws BusinessException;


}
