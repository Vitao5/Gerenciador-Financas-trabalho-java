package com.mycompany.sistemafinanceiro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    public Connection conectarBanco() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/fiscalize_financas";
        String usuario = "usuario";
        String pass = "senha";

        return DriverManager.getConnection(url, usuario, pass);
    }
}
