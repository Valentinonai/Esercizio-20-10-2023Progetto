package org.example;


import com.github.javafaker.Faker;
import com.sun.tools.javac.Main;
import org.example.entities.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

      
    }


}
