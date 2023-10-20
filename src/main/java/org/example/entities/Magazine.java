package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "magazines")
public class Magazine extends Item {
    @Enumerated(EnumType.STRING)
    @Column(name = "periodicita")
    private Periodicita periodicita;

    public Magazine() {
    }

    public Magazine(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicita=" + periodicita +
                ", isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
