package com.mycompany.sistemafinanceiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.sistemafinanceiro.MODEL.Usuario;
import com.mycompany.sistemafinanceiro.util.conexao;

public class UsuarioDAO {

    public UsuarioDAO() {
    }

    public Usuario salvarUsuario(Usuario user) throws SQLException {
        String sql = "INSERT INTO USUARIO (cpf, nome, email, senha) VALUES (?, ?, ?, ?);";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getCpf());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getSenha());

            int verif = stmt.executeUpdate();
            if (verif > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setIdUsuario(rs.getInt(1));
                    }
                }
            }
        }
        return user;
    }

    public void deletarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar usuario: " + e.getMessage());
        }
    }

    public Usuario atualizarUsuario(Usuario user) throws SQLException {
        String sql = "UPDATE USUARIO SET cpf = ?, nome = ?, email = ?, senha = ? WHERE idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getCpf());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getSenha());
            stmt.setInt(5, user.getIdUsuario());
            stmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuario: " + e.getMessage());
        }

    }

    public Usuario buscarUsuario(String email) throws SQLException {
        String sql = "SELECT idUsuario, cpf, nome, email, senha FROM USUARIO WHERE email = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuario: " + e.getMessage());
        }
        return null;
    }

    public Usuario buscarUsuarioPorCPF(String cpf) throws SQLException {
        String sql = "SELECT idUsuario, cpf, nome, email, senha FROM USUARIO WHERE cpf = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuario: " + e.getMessage());
        }

    }

    public List<Usuario> buscarTodosUsuarios() throws SQLException {
        String sql = "SELECT idUsuario, cpf, nome, email, senha FROM USUARIO;";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }
            return usuarios;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todos os usuarios: " + e.getMessage());
        }

    }

    public Usuario buscarUsuarioPorID(int id) throws SQLException {
        String sql = "SELECT idUsuario, cpf, nome, email FROM USUARIO WHERE idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), null);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuario: " + e.getMessage());
        }

    }
}
