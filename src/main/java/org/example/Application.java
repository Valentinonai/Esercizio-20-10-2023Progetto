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
import java.util.*;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Faker fkr = new Faker();
    private static final Random rnd = new Random();
    private static final Scanner input = new Scanner(System.in);

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerFactory");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        List<Item> itemList = new ArrayList<>();
        Set<String> isbn = new HashSet<>();
        boolean bool = false;
        ItemDao itemDao = new ItemDao(em);
        UtenteDao utenteDao = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);


        // fillDb(em);


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
            Utente u = new Utente(fkr.name().firstName(), fkr.name().lastName(), LocalDate.of(rnd.nextInt(1950, 2024), rnd.nextInt(1, 13), rnd.nextInt(1, 29)), rnd.nextLong(1000000, 5000000));
            utenteDao.save(u);
        }
    }
}
