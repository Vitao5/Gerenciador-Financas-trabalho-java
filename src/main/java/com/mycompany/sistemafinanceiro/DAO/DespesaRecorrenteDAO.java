package com.mycompany.sistemafinanceiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.sistemafinanceiro.MODEL.DespesaRecorrente;
import com.mycompany.sistemafinanceiro.util.conexao;

public class DespesaRecorrenteDAO {

    public DespesaRecorrenteDAO() {
    }

    public DespesaRecorrente salvarDespesaRecorrente(DespesaRecorrente despesaRecorrente) throws SQLException {
        String sql = "INSERT INTO DESPESA_RECORRENTE (valor, descricao, categoria, idUsuario, idFormaPagamento, valorMensalidade) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, despesaRecorrente.getValor());
            stmt.setString(2, despesaRecorrente.getDescricao());
            stmt.setString(3, despesaRecorrente.getCategoria());
            stmt.setInt(4, despesaRecorrente.getIdUsuario());
            stmt.setInt(5, despesaRecorrente.getIdFormaPagamento());
            stmt.setDouble(6, despesaRecorrente.getValorMensalidade());

            int verif = stmt.executeUpdate();
            if (verif > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        despesaRecorrente.setIdDespesa(idGerado);
                    }
                }
            }
            return despesaRecorrente;
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar despesa recorrente: " + e.getMessage());
        }

    }

    public DespesaRecorrente atualizarDespesaRecorrente(DespesaRecorrente despesaRecorrente) throws SQLException {
        String sql = "UPDATE DESPESA_RECORRENTE SET valor = ?, descricao = ?, categoria = ?, idUsuario = ?, idFormaPagamento = ?, valorMensalidade = ? WHERE idDespesa = ?;";

        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, despesaRecorrente.getValor());
            stmt.setString(2, despesaRecorrente.getDescricao());
            stmt.setString(3, despesaRecorrente.getCategoria());
            stmt.setInt(4, despesaRecorrente.getIdUsuario());

            if (despesaRecorrente.getIdFormaPagamento() != null) {
                stmt.setInt(5, despesaRecorrente.getIdFormaPagamento());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            if (despesaRecorrente.getValorMensalidade() != null) {
                stmt.setDouble(6, despesaRecorrente.getValorMensalidade());
            } else {
                stmt.setNull(6, java.sql.Types.DECIMAL);
            }

            stmt.setInt(7, despesaRecorrente.getIdDespesa());
            stmt.executeUpdate();
            return despesaRecorrente;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar despesa recorrente: " + e.getMessage());
        }

    }

    public DespesaRecorrente deletarDespesaRecorrente(DespesaRecorrente despesaRecorrente) throws SQLException {
        String sql = "DELETE FROM DESPESA_RECORRENTE WHERE idDespesa = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, despesaRecorrente.getIdDespesa());
            stmt.executeUpdate();
            return despesaRecorrente;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar despesa recorrente: " + e.getMessage());
        }

    }

    public DespesaRecorrente buscarDespesaRecorrente(int idDespesa) throws SQLException {
        String sql = "SELECT idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento, valorMensalidade FROM DESPESA_RECORRENTE WHERE idDespesa = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDespesa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer idFormaPagamento = rs.getInt(6);
                    if (rs.wasNull()) {
                        idFormaPagamento = null;
                    }
                    Double valorMensalidade = rs.getDouble(7);
                    if (rs.wasNull()) {
                        valorMensalidade = null;
                    }
                    return new DespesaRecorrente(
                            rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), idFormaPagamento, valorMensalidade);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar despesa recorrente: " + e.getMessage());
        }

    }

    public List<DespesaRecorrente> buscarDespesasRecorrentesPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento, valorMensalidade FROM DESPESA_RECORRENTE WHERE idUsuario = ?;";
        List<DespesaRecorrente> despesasRecorrentes = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer idFormaPagamento = rs.getInt(6);
                    if (rs.wasNull()) {
                        idFormaPagamento = null;
                    }
                    Double valorMensalidade = rs.getDouble(7);
                    if (rs.wasNull()) {
                        valorMensalidade = null;
                    }
                    despesasRecorrentes.add(new DespesaRecorrente(
                            rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), idFormaPagamento, valorMensalidade));
                }
            }
            return despesasRecorrentes;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar despesas recorrentes por usuario: " + e.getMessage());
        }

    }
}