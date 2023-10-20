package org.example;


import com.github.javafaker.Faker;
import com.sun.tools.javac.Main;
import org.example.DAO.ItemDao;
import org.example.DAO.PrestitoDao;
import org.example.DAO.UtenteDao;
import org.example.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Faker fkr = new Faker();
    private static final Random rnd = new Random();


    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerFactory");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        ItemDao itemDao = new ItemDao(em);
        UtenteDao utenteDao = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);


        // fillDb(em);
        //  creaPrestito(utenteDao.getById(108), itemDao.getById(109), em);

        //*****************SALVATAGGIO ELEMENTO A CATALOGO**************************
        Book b = new Book(fkr.book().title(), 1998, rnd.nextInt(50, 1000), fkr.book().author(), fkr.book().genre());
        // aggiungiElemento(b, em);

        //*****************ELIMINA ELEMENTO A CATALOGO******************************

        // eliminaElemento(74, em);

        //******************RICERCA PER ISBN****************************************

        Item i = ricercaPerId(97, em);
        System.out.println("*************************************************RICERCA PER ISBN************************************");
        if (i != null)
            System.out.println(i);
        else System.out.println("ELEMENTO NON TROVATO");

        //******************RICERCA PER ANNO****************************************

        List<Item> rpa = ricercaPerAnno(1997, em);
        System.out.println("*************************************************RICERCA PER ANNO************************************");
        if (rpa.size() == 0)
            System.out.println("NESSUN ELEMENTO TROVATO");
        else
            rpa.forEach(System.out::println);

        //******************RICERCA PER AUTORE****************************************

        List<Book> rpat = ricercaPerAutore("Beverly Nicolas", em);
        System.out.println("*************************************************RICERCA PER AUTORE************************************");
        if (rpat.size() == 0)
            System.out.println("NESSUN ELEMENTO TROVATO");
        else
            rpat.forEach(System.out::println);

        //******************RICERCA PER TITOLO O PARTE DI TITOLO****************************************


        List<Item> rpt = ricercaPerTitolo("The", em);
        System.out.println("*************************************************RICERCA PER TITOLO O PARTE DI TITOLO************************************");
        if (rpt.size() == 0)
            System.out.println("NESSUN ELEMENTO TROVATO");
        else
            rpt.forEach(System.out::println);

        //******************RICERCA ELEMENTI ATTUALMENTE IN PRESTITO PER TESSERA UTENTE****************************************


        List<Item> rp = elementiInPrestito(96, em);
        System.out.println("*************************************************RICERCA ELEMENTI ATTUALMENTE IN PRESTITO PER TESSERA UTENTE************************************");
        if (rp.size() == 0)
            System.out.println("NON CI SONO ELEMENTI ATTUALMENTE IN PRESTITO PER QUESTO UTENTE");
        else
            rp.forEach(System.out::println);

        //******************RICERCA PRESTITI SCADUTI NON RESTITUITI****************************************
        List<Prestito> p = prestitiScadutiNonRestituiti(em);
        System.out.println("*************************************************RICERCA PRESTITI SCADUTI NON RESTITUITI************************************");
        if (p.size() == 0) {
            System.out.println("NON CI SONO PRESTITI SCADUTI NON RESTITUITI");
        } else {
            p.forEach(System.out::println);
        }

        // utenteDao.delete(141);  //ELIMINA A CASCATA ANCHE I PRESTITI COLLEGATI AD UN UTENTE ELIMINATO

        // itemDao.delete(109);    //ELIMINA A CASCATA ANCHE I PRESTITI COLLEGATI AD UN ITEM ELIMINATO

        em.close();
        emf.close();

    }

    public static void fillDb(EntityManager em) {
        ItemDao itemDao = new ItemDao(em);
        UtenteDao utenteDao = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);

        for (int i = 0; i < 10; i++) {
            Book b = new Book(fkr.book().title(), rnd.nextInt(1900, 2024), rnd.nextInt(50, 1000), fkr.book().author(), fkr.book().genre());
            itemDao.save(b);
            int n = rnd.nextInt(1, 4);
            Magazine m = new Magazine(fkr.book().title(), rnd.nextInt(1900, 2024), rnd.nextInt(50, 1000), n == 1 ? Periodicita.SETTIMANALE : n == 2 ? Periodicita.MENSILE : Periodicita.SEMESTRALE);
            itemDao.save(m);
            Utente u = new Utente(fkr.name().firstName(), fkr.name().lastName(), LocalDate.of(rnd.nextInt(1950, 2024), rnd.nextInt(1, 13), rnd.nextInt(1, 29)));
            utenteDao.save(u);
        }
    }

    public static void creaPrestito(Utente u, Item i, EntityManager em) {

        LocalDate l = LocalDate.now();

        Prestito p = new Prestito(u, i, l, l.plusDays(30));
        PrestitoDao prestitoDao = new PrestitoDao(em);
        prestitoDao.save(p);
    }

    public static void aggiungiElemento(Item i, EntityManager em) {
        ItemDao itemdao = new ItemDao(em);
        itemdao.save(i);
    }

    public static void eliminaElemento(long id, EntityManager em) {
        ItemDao itemdao = new ItemDao(em);
        itemdao.delete(id);
    }

    public static Item ricercaPerId(long id, EntityManager em) {
        ItemDao itemDao = new ItemDao(em);
        return itemDao.getById(id);
    }

    public static List<Item> ricercaPerAnno(int anno, EntityManager em) {
        ItemDao itemDao = new ItemDao(em);
        return itemDao.ricercaPerAnno(anno);
    }

    public static List<Book> ricercaPerAutore(String autore, EntityManager em) {
        ItemDao itemDao = new ItemDao(em);
        return itemDao.ricercaPerAutore(autore);
    }

    public static List<Item> ricercaPerTitolo(String titolo, EntityManager em) {
        ItemDao itemDao = new ItemDao(em);
        return itemDao.ricercaPerTitolo(titolo);
    }

    public static List<Item> elementiInPrestito(long tessera, EntityManager em) {
        PrestitoDao prestitoDao = new PrestitoDao(em);
        return prestitoDao.elementiInPrestito(tessera);

    }

    public static List<Prestito> prestitiScadutiNonRestituiti(EntityManager em) {
        PrestitoDao prestitoDao = new PrestitoDao(em);
        return prestitoDao.prestitiScaduti();
    }
}
