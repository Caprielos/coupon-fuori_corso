package it.univaq.disim.oop.domain;

import java.util.HashSet;
import java.util.Set;

public class Cliente extends Utente {

    private String nome;

    private String cognome;

    private String coupon;

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

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Set<Coupon> getCouponSet() {
        return couponSet;
    }

    public void setCouponSet(Set<Coupon> couponSet) {
        this.couponSet = couponSet;
    }
}
