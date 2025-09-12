package br.com.dasa.estoque.db;

import java.sql.*;

public class ConnectionFactory {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm557230";
    private static final String PASSWORD = "170105";

    public static Connection getConnecion() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do Oracle não encontrado.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
