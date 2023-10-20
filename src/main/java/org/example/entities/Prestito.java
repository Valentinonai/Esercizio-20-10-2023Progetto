package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "data_prestito", nullable = false)
    private LocalDate data_prestito;
    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate data_restituzione_prevista;
    @Column(name = "data_restituzione_effettiva")
    private LocalDate data_restituzione_effettiva;

    public Prestito() {

    }

    public Prestito(Utente utente, Item item, LocalDate data_prestito, LocalDate data_restituzione_prevista, LocalDate data_restituzione_effettiva) {
        this.utente = utente;
        this.item = item;
        this.data_prestito = data_prestito;
        this.data_restituzione_prevista = data_restituzione_prevista;
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    public long getId() {
        return id;
    }


    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getData_prestito() {
        return data_prestito;
    }

    public void setData_prestito(LocalDate data_prestito) {
        this.data_prestito = data_prestito;
    }

    public LocalDate getData_restituzione_prevista() {
        return data_restituzione_prevista;
    }

    public void setData_restituzione_prevista(LocalDate data_restituzione_prevista) {
        this.data_restituzione_prevista = data_restituzione_prevista;
    }

    public LocalDate getData_restituzione_effettiva() {
        return data_restituzione_effettiva;
    }

    public void setData_restituzione_effettiva(LocalDate data_restituzione_effettiva) {
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", item=" + item +
                ", data_prestito=" + data_prestito +
                ", data_restituzione_prevista=" + data_restituzione_prevista +
                ", data_restituzione_effettiva=" + data_restituzione_effettiva +
                '}';
    }
}
