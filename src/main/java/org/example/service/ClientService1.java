package org.example.service;

import org.example.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService1 {
    private PreparedStatement preparedStatement;

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
    }

    private Connection getConnection() throws SQLException {
        Database database = Database.getInstance();
        return database.getConnection();
    }

    private String[] parseScriptFile() throws IOException {
        StringBuilder sql = new StringBuilder();
        List<String> queries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("sql/clients.sql"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.endsWith(";")) {
                    queries.add(sql.toString());
                    sql.setLength(0);
                }
            }
        }
        return queries.toArray(new String[0]);
    }

    public long create(String name) {
        long id = 0;
        try {
            Connection connection = getConnection();
            String[] queries = parseScriptFile();
            for (String query : queries) {
                if (query.contains("INSERT INTO client(name) VALUES(?)")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.executeUpdate();
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        while (generatedKeys.next()) {
                            id = generatedKeys.getInt("id");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getById(long id) {
        String name = null;
        try {
            Connection connection = getConnection();
            String[] queries = parseScriptFile();
            for (String query : queries) {
                if (query.contains("SELECT name FROM client where ID = ?")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setLong(1, id);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            name = resultSet.getString("name");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setName(long id, String name) {
        try {
            Connection connection = getConnection();
            String[] queries = parseScriptFile();
            for (String query : queries) {
                if (query.contains("UPDATE client SET name = ? WHERE id = ?")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setLong(2, id);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            Connection connection = getConnection();
            String[] queries = parseScriptFile();
            for (String query : queries) {
                if (query.contains("DELETE FROM PROJECT WHERE ID = ?")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setLong(1, id);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (query.contains("DELETE FROM CLIENT WHERE ID = ?")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setLong(1, id);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String[] queries = parseScriptFile();
            for (String query : queries) {
                if (query.contains("SELECT * FROM CLIENT")) {
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        Client client = new Client(id, name);
                        clients.add(client);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    class Client {
        private int id;
        private String name;

        public Client(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return " " + id + " " + name + '\n';
        }
    }
}