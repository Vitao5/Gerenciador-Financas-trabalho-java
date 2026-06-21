package com.mycompany.sistemafinanceiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.sistemafinanceiro.MODEL.DespesaExtra;
import com.mycompany.sistemafinanceiro.util.conexao;

public class DespesExtraDAO {

    public DespesExtraDAO() {
    }

    public DespesaExtra salvarDespesaExtra(DespesaExtra despesaExtra) throws SQLException {
        String sql = "INSERT INTO DESPESA_EXTRA (valor, descricao, categoria, idUsuario, idFormaPagamento) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, despesaExtra.getValor());
            stmt.setString(2, despesaExtra.getDescricao());
            stmt.setString(3, despesaExtra.getCategoria());
            stmt.setInt(4, despesaExtra.getIdUsuario());
            stmt.setInt(5, despesaExtra.getIdFormaPagamento());

            int verif = stmt.executeUpdate();
            if (verif > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        despesaExtra.setIdDespesa(idGerado);
                    }
                }
            }
            return despesaExtra;
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar despesa extra: " + e.getMessage());
        }

    }

    public DespesaExtra atualizarDespesaExtra(DespesaExtra despesaExtra) throws SQLException {
        String sql = "UPDATE DESPESA_EXTRA SET valor = ?, descricao = ?, categoria = ?, idUsuario = ?, idFormaPagamento = ? WHERE idDespesa = ?;";

        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, despesaExtra.getValor());
            stmt.setString(2, despesaExtra.getDescricao());
            stmt.setString(3, despesaExtra.getCategoria());
            stmt.setInt(4, despesaExtra.getIdUsuario());
            stmt.setInt(5, despesaExtra.getIdFormaPagamento());

            stmt.setInt(6, despesaExtra.getIdDespesa());
            stmt.executeUpdate();
            return despesaExtra;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar despesa extra: " + e.getMessage());
        }

    }

    public DespesaExtra deletarDespesaExtra(DespesaExtra despesaExtra) throws SQLException {
        String sql = "DELETE FROM DESPESA_EXTRA WHERE idDespesa = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, despesaExtra.getIdDespesa());
            stmt.executeUpdate();
            return despesaExtra;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar despesa extra: " + e.getMessage());
        }

    }

    public DespesaExtra buscarDespesaExtra(int idDespesa) throws SQLException {
        String sql = "SELECT idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento FROM DESPESA_EXTRA WHERE idDespesa = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDespesa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer idFormaPagamento = rs.getInt(6);
                    if (rs.wasNull()) {
                        idFormaPagamento = null;
                    }
                    return new DespesaExtra(
                            rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), idFormaPagamento);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar despesa extra: " + e.getMessage());
        }

    }

    public List<DespesaExtra> buscarDespesasExtrasPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento FROM DESPESA_EXTRA WHERE idUsuario = ?;";
        List<DespesaExtra> despesasExtras = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer idFormaPagamento = rs.getInt(6);
                    if (rs.wasNull()) {
                        idFormaPagamento = null;
                    }
                    despesasExtras.add(new DespesaExtra(
                            rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), idFormaPagamento));
                }
            }
            return despesasExtras;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar despesas extras por usuario: " + e.getMessage());
        }

    }
}
