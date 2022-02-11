package it.univaq.disim.oop.domain;

import java.util.HashSet;
import java.util.Set;

public class Cliente extends Utente {

    private String nome;
    private String cognome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    private Set<Coupon> couponSet = new HashSet<>();

}
