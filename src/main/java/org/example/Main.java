package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:C:/data/sample2", "sa","sa").load();

        flyway.migrate();
    }
}