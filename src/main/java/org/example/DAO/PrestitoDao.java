package org.example.DAO;

import org.example.entities.Item;
import org.example.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }


    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Prestito getById(long id) {
        return em.find(Prestito.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Prestito found = em.find(Prestito.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Elemento eliminato");
            } else System.out.println("Elemento non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Item> elementiInPrestito(long tessera) {
        TypedQuery<Item> q = em.createQuery("SELECT i FROM Item i Join Prestito p ON i.isbn=p.item WHERE p.utente=" + tessera + " AND p.data_restituzione_effettiva=null", Item.class);
//        q.setParameter("tessera", tessera);
        return q.getResultList();

    }

    public List<Prestito> prestitiScaduti() {
        LocalDate now = LocalDate.now();
        TypedQuery<Prestito> q = em.createQuery("SELECT p FROM Prestito p WHERE p.data_restituzione_prevista<:now AND p.data_restituzione_effettiva=null", Prestito.class);
        q.setParameter("now", now);
        return q.getResultList();

    }
}
