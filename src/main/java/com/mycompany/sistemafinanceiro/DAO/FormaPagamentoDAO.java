package com.mycompany.sistemafinanceiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.sistemafinanceiro.MODEL.FormaPagamento;
import com.mycompany.sistemafinanceiro.util.conexao;

public class FormaPagamentoDAO {

    public FormaPagamentoDAO() {
    }

    public FormaPagamento cadastrarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        String sql = "INSERT INTO FORMA_PAGAMENTO (descricao, idUsuario) VALUES (?, ?);";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, formaPagamento.getDescricao());
            stmt.setInt(2, formaPagamento.getIdUsuario());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    formaPagamento.setIdFormaPagamento(rs.getInt(1));
                }
            }
            return formaPagamento;
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar forma de pagamento: " + e.getMessage());
        }

    }

    public FormaPagamento atualizarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        String sql = "UPDATE FORMA_PAGAMENTO SET descricao = ? WHERE idFormaPagamento = ? AND idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, formaPagamento.getDescricao());
            stmt.setInt(2, formaPagamento.getIdFormaPagamento());
            stmt.setInt(3, formaPagamento.getIdUsuario());
            stmt.execute();
            return formaPagamento;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar forma de pagamento: " + e.getMessage());
        }

    }

    public FormaPagamento deletarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        String sql = "DELETE FROM FORMA_PAGAMENTO WHERE idFormaPagamento = ? AND idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, formaPagamento.getIdFormaPagamento());
            stmt.setInt(2, formaPagamento.getIdUsuario());
            stmt.execute();
            return formaPagamento;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar forma de pagamento: " + e.getMessage());
        }

    }

    public FormaPagamento buscarFormaPagamentoPorIdEUsuario(int idFormaPagamento, int idUsuario) throws SQLException {
        String sql = "SELECT idFormaPagamento, descricao, idUsuario FROM FORMA_PAGAMENTO WHERE idFormaPagamento = ? AND idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFormaPagamento);
            stmt.setInt(2, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FormaPagamento(rs.getInt(1), rs.getString(2), rs.getInt(3));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar forma de pagamento: " + e.getMessage());
        }

    }

    public List<FormaPagamento> buscarFormasPagamentoPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT idFormaPagamento, descricao, idUsuario FROM FORMA_PAGAMENTO WHERE idUsuario = ?;";
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    formasPagamento.add(new FormaPagamento(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
            return formasPagamento;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar formas de pagamento do usuario: " + e.getMessage());
        }

    }
}
