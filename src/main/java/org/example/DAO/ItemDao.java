package org.example.DAO;

import org.example.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
