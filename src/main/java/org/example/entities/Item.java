package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "ricerca_per_anno", query = "SELECT i FROM Item i WHERE i.annoPubblicazione=:a")

public abstract class Item {
    @Id
    @GeneratedValue
    protected long isbn;
    @Column(name = "titolo", nullable = false)
    protected String titolo;
    @Column(name = "anno_pubblicazione", nullable = false)
    protected int annoPubblicazione;
    @Column(name = "numero_pagine", nullable = false)
    protected int numeroPagine;
    @OneToMany(mappedBy = "item")
    private List<Prestito> lista_prestiti;

    public Item() {

    }

    public Item(String titolo, int annoPubblicazione, int numeroPagine) {

        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public long getIsbn() {
        return isbn;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    @Override
    public String toString() {
        return "Item{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
