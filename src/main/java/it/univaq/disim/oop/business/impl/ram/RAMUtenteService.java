package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.UtenteService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Utente;

import java.util.ArrayList;
import java.util.List;

public class RAMUtenteService implements UtenteService {

    public static List<Utente> utentiRegistrati = new ArrayList<>();
    public static Integer id = 1;

    @Override
    public Utente authenticate(String email, String password) throws BusinessException {
        Utente prova = new Utente();
        if("c".equalsIgnoreCase(email) && "c".equalsIgnoreCase(password)) {
            prova = new Cliente();
            prova.setUsername("aaaa");
            return prova;
        }

        for (Utente utente : utentiRegistrati) {
            if (utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
                return utente;
            }
        }
        return null;
    }

    @Override
    public void recordUser(Utente utente) throws BusinessException {
        utente.setId(++id);
        utentiRegistrati.add(utente);

    }
}
