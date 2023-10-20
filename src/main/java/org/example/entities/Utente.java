package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    private long numero_di_tessera;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @Column(name = "data_di_nascita")
    private LocalDate data_di_nascita;
    @OneToMany(mappedBy = "utente")
    private List<Prestito> lista_prestiti;

    public Utente() {

    }

    public Utente(String nome, String cognome, LocalDate data_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;

    }


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

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public long getNumero_di_tessera() {
        return numero_di_tessera;
    }


    @Override
    public String toString() {
        return "Utente{" +
              
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_di_nascita=" + data_di_nascita +
                ", numero_di_tessera=" + numero_di_tessera +
                '}';
    }
}
