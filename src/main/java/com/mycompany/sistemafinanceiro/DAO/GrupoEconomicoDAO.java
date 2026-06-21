package com.mycompany.sistemafinanceiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.sistemafinanceiro.MODEL.Despesa;
import com.mycompany.sistemafinanceiro.MODEL.GrupoEconomico;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;
import com.mycompany.sistemafinanceiro.util.conexao;

public class GrupoEconomicoDAO {

    public GrupoEconomicoDAO() {
    }

    public GrupoEconomico cadastrarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        String sql = "INSERT INTO GRUPO_ECONOMICO (nomeGrupo, idAdmin) VALUES (?, ?);";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, grupoEconomico.getNomeGrupo());
            stmt.setInt(2, grupoEconomico.getIdAdmin());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    grupoEconomico.setIdGrupo(rs.getInt(1));
                }
            }
            return grupoEconomico;
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar grupo economico: " + e.getMessage());
        }
    }

    public GrupoEconomico atualizarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        String sql = "UPDATE GRUPO_ECONOMICO SET nomeGrupo = ? WHERE idGrupo = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grupoEconomico.getNomeGrupo());
            stmt.setInt(2, grupoEconomico.getIdGrupo());
            stmt.executeUpdate();
            return grupoEconomico;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar grupo economico: " + e.getMessage());
        }
    }

    public GrupoEconomico deletarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        String sql = "DELETE FROM GRUPO_ECONOMICO WHERE idGrupo = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoEconomico.getIdGrupo());
            stmt.executeUpdate();
            return grupoEconomico;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar grupo economico: " + e.getMessage());
        }
    }

    public GrupoEconomico buscarGrupoEconomicoPorID(int idGrupo, int idUsuario) throws SQLException {
        String sql = "SELECT g.idGrupo, g.nomeGrupo, g.idAdmin FROM GRUPO_ECONOMICO g JOIN GRUPO_PARTICIPANTE p ON p.idGrupo = g.idGrupo WHERE g.idGrupo = ? AND p.idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GrupoEconomico(rs.getInt(1), rs.getString(2), rs.getInt(3));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar grupo economico: " + e.getMessage());
        }
    }

    public List<GrupoEconomico> buscarGruposUsuarioParticipante(int idUsuario) throws SQLException {
        String sql = "SELECT p.idGrupo, p.nomeGrupo, p.idAdmin  FROM grupo_participante g JOIN grupo_economico p ON p.idGrupo = g.idGrupo WHERE g.idUsuario = ?;";
        List<GrupoEconomico> gruposEconomicos = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gruposEconomicos.add(new GrupoEconomico(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
            return gruposEconomicos;
        } catch (SQLException e) {
            throw new SQLException("ERRO AO BUSCAR OS GRUPOS QUE A PESSOA PERTENCE: " + e.getMessage());
        }
    }

    public void adicionarParticipante(int idGrupo, int idUsuario) throws SQLException {
        String sql = "INSERT INTO GRUPO_PARTICIPANTE (idGrupo, idUsuario) VALUES (?, ?);";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar participante ao grupo: " + e.getMessage());
        }
    }

    public void removerParticipante(int idGrupo, int idUsuario) throws SQLException {
        String sql = "DELETE FROM GRUPO_PARTICIPANTE WHERE idGrupo = ? AND idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover participante do grupo: " + e.getMessage());
        }
    }

    public List<Usuario> buscarParticipantes(int idGrupo) throws SQLException {
        String sql = "SELECT u.idUsuario, u.cpf, u.nome, u.email, u.senha "
                + "FROM USUARIO u "
                + "INNER JOIN GRUPO_PARTICIPANTE gp ON u.idUsuario = gp.idUsuario "
                + "WHERE gp.idGrupo = ?;";
        List<Usuario> participantes = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    participantes.add(new Usuario(
                            rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5)));
                }
            }
            return participantes;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar participantes do grupo: " + e.getMessage());
        }
    }

    public boolean verificarParticipante(int idGrupo, int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM GRUPO_PARTICIPANTE WHERE idGrupo = ? AND idUsuario = ?;";
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException("ERRO AO VERIFICAR GRUPO: " + e.getMessage());
        }
    }

    public List<Despesa> listaDespesasDoGrupo(int idGrupo) throws SQLException {
        String sql = "SELECT D.idDespesa, D.valor, D.descricao, D.categoria, D.idUsuario, D.idFormaPagamento "
                + "FROM DESPESA D "
                + "JOIN GRUPO_PARTICIPANTE GP ON D.idUsuario = GP.idUsuario "
                + "WHERE GP.idGrupo = ?";
        List<Despesa> despesas = new ArrayList<>();
        try (Connection conn = new conexao().conectarBanco();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGrupo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer idFormaPagamento = rs.getInt(6);
                    if (rs.wasNull()) {
                        idFormaPagamento = null;
                    }
                    despesas.add(new Despesa(
                            rs.getInt(1), rs.getDouble(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5), idFormaPagamento));
                }
            }
            return despesas;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar despesas do grupo: " + e.getMessage());
        }
    }
}
