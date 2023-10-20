package org.example.DAO;

import org.example.entities.Book;
import org.example.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ItemDao {
    private EntityManager em;

    public ItemDao(EntityManager em) {
        this.em = em;
    }


    public void save(Item item) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(item);
            t.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Item getById(long id) {
        return em.find(Item.class, id);
    }

    public void delete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Item found = em.find(Item.class, id);
            if (found != null) {
                em.remove(found);
                t.commit();
                System.out.println("Elemento eliminato");
            } else System.out.println("Elemento non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Item> ricercaPerAnno(int anno) {
        TypedQuery<Item> q = em.createNamedQuery("ricerca_per_anno", Item.class);
        q.setParameter("a", anno);
        return q.getResultList();
    }

    public List<Book> ricercaPerAutore(String autore) {
        TypedQuery<Book> q = em.createNamedQuery("ricerca_per_autore", Book.class);
        q.setParameter("a", autore);
        return q.getResultList();
    }

    public List<Item> ricercaPerTitolo(String titolo) {
        TypedQuery<Item> q = em.createNamedQuery("ricerca_per_titolo", Item.class);
        q.setParameter("t", titolo);
        return q.getResultList();
    }
}
