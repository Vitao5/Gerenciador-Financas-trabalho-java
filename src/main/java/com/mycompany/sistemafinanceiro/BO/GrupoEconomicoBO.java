package com.mycompany.sistemafinanceiro.BO;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.sistemafinanceiro.DAO.GrupoEconomicoDAO;
import com.mycompany.sistemafinanceiro.MODEL.Despesa;
import com.mycompany.sistemafinanceiro.MODEL.GrupoEconomico;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

public class GrupoEconomicoBO {

    private GrupoEconomicoDAO grupoEconomicoDAO = new GrupoEconomicoDAO();

    public GrupoEconomico cadastrarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        GrupoEconomico grupoCriado = grupoEconomicoDAO.cadastrarGrupoEconomico(grupoEconomico);

        if (grupoCriado != null) {
            grupoEconomicoDAO.adicionarParticipante(grupoCriado.getIdGrupo(), grupoCriado.getIdAdmin());
        }
        return grupoCriado;
    }

    public GrupoEconomico atualizarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        return grupoEconomicoDAO.atualizarGrupoEconomico(grupoEconomico);
    }

    public GrupoEconomico deletarGrupoEconomico(GrupoEconomico grupoEconomico) throws SQLException {
        return grupoEconomicoDAO.deletarGrupoEconomico(grupoEconomico);
    }

    public GrupoEconomico buscarGrupoEconomico(int idGrupo, int idUsuario) throws SQLException {
        return grupoEconomicoDAO.buscarGrupoEconomicoPorID(idGrupo, idUsuario);
    }

    public List<GrupoEconomico> buscarGruposUsuarioParticipanteBO(int idUsuario) throws SQLException {
        return grupoEconomicoDAO.buscarGruposUsuarioParticipante(idUsuario);
    }

    public void adicionarParticipante(int idGrupo, int idUsuario) throws SQLException {

        if (grupoEconomicoDAO.verificarParticipante(idGrupo, idUsuario)) {
            throw new SQLException("ESTE USUARIO JA E PARTICIPANTE DESTE GRUPO");
        }
        grupoEconomicoDAO.adicionarParticipante(idGrupo, idUsuario);
    }

    public void removerParticipante(int idGrupo, int idUsuario) throws SQLException {

        GrupoEconomico grupo = grupoEconomicoDAO.buscarGrupoEconomicoPorID(idGrupo, idUsuario);

        if (grupo != null && grupo.getIdAdmin() != idUsuario) {
            throw new SQLException("SOMENTE O ADMIN DO GRUPO PODE REMOVER PARTICIPANTES.");
        }

        if (!grupoEconomicoDAO.verificarParticipante(idGrupo, idUsuario)) {
            throw new SQLException("USUARIO NAO PARTICIPA DO GRUPO");
        }
        grupoEconomicoDAO.removerParticipante(idGrupo, idUsuario);
    }

    public List<Usuario> buscarParticipantes(int idGrupo) throws SQLException {
        return grupoEconomicoDAO.buscarParticipantes(idGrupo);
    }

    public List<Despesa> buscarDespesasDoGrupo(int idGrupo) throws SQLException {
        List<Despesa> despesas = grupoEconomicoDAO.listaDespesasDoGrupo(idGrupo);

        if (despesas.size() == 0) {
            throw new SQLException("NENHUMA DESPESA ENCONTRADA PARA ESTE GRUPO");
        }

        return despesas;
    }
}
