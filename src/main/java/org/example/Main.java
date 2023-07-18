package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.migracia();
    }

    public void migracia(){
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:tcp://localhost/~/init_db", "sa",null).load();

        flyway.migrate();
    }
}