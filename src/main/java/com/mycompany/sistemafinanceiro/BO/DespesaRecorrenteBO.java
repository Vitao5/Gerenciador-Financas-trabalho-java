package com.mycompany.sistemafinanceiro.BO;

import com.mycompany.sistemafinanceiro.DAO.DespesaRecorrenteDAO;
import com.mycompany.sistemafinanceiro.DAO.UsuarioDAO;
import com.mycompany.sistemafinanceiro.MODEL.DespesaRecorrente;

import com.mycompany.sistemafinanceiro.util.DespesaRecorrenteUtil;
import com.mycompany.sistemafinanceiro.util.UsuarioUtil;

import java.sql.SQLException;
import java.util.List;

public class DespesaRecorrenteBO {
    DespesaRecorrenteDAO despesaRecorrenteDAO;
    UsuarioDAO usuarioDAO;
    UsuarioUtil usuarioUtil;
    DespesaRecorrenteUtil despesaRecorrenteUtil;
    FormaPagamentoBO formaPagamentoBO;

    public DespesaRecorrenteBO() {
        despesaRecorrenteDAO = new DespesaRecorrenteDAO();
        usuarioDAO = new UsuarioDAO();
        usuarioUtil = new UsuarioUtil();
        despesaRecorrenteUtil = new DespesaRecorrenteUtil();
        formaPagamentoBO = new FormaPagamentoBO();
    }

    public void cadastrarDespesaRecorrente(DespesaRecorrente despesaRecorrente, String cpf) throws SQLException {
        if (usuarioUtil.buscaUsuarioPorCPFUtil(cpf) == null) {
            System.out.println("\n\nUSUARIO NAO EXISTE AO TENTAR CADASTRAR UMA DESPESA");
            return;
        }

        if (despesaRecorrenteUtil.validaObjetoDespesaRecorrente(despesaRecorrente)) {
            despesaRecorrenteDAO.salvarDespesaRecorrente(despesaRecorrente);
            System.out.println("\n\nDESPESA RECORRENTE CADASTRADA COM SUCESSO");
        } else {
            System.out.println("\n\nDESPESA RECORRENTE NAO CADASTRADA");
        }
    }

    public List<DespesaRecorrente> listarDespesasRecorrentes(String cpf, int idUsuario) throws SQLException {

        if (usuarioUtil.buscaUsuarioPorCPFUtil(cpf) == null) {
            System.out.println("\n\nUSUARIO NAO EXISTE AO TENTAR LISTAR DESPESAS RECORRENTES");
            return null;
        }

        return despesaRecorrenteDAO.buscarDespesasRecorrentesPorUsuario(idUsuario);
    }

    public DespesaRecorrente buscarDespesaRecorrentePorId(int idDespesaRecorrente) throws SQLException {
        DespesaRecorrente despesaRecorrente = despesaRecorrenteDAO.buscarDespesaRecorrente(idDespesaRecorrente);
        if (despesaRecorrente == null) {
            System.out.println("\n\nDESPESA RECORRENTE NAO ENCONTRADA");
            return null;
        }

        return despesaRecorrente;
    }

    public void atualizarDespesaRecorrente(DespesaRecorrente despesaRecorrente) throws SQLException {
        if (despesaRecorrenteDAO.buscarDespesaRecorrente(despesaRecorrente.getIdDespesa()) == null) {
            System.out.println("\n\nDESPESA RECORRENTE NAO ENCONTRADA");
            return;
        }

        if (despesaRecorrenteUtil.validaObjetoDespesaRecorrente(despesaRecorrente)) {
            despesaRecorrenteDAO.atualizarDespesaRecorrente(despesaRecorrente);
            System.out.println("\n\nDESPESA RECORRENTE ATUALIZADA COM SUCESSO");
        } else {
            System.out.println("\n\nDESPESA RECORRENTE NAO ATUALIZADA");
        }
    }

    public void deletarDespesaRecorrente(DespesaRecorrente despesaRecorrente) throws SQLException {
        if (despesaRecorrenteDAO.buscarDespesaRecorrente(despesaRecorrente.getIdDespesa()) == null) {
            System.out.println("\n\nDESPESA RECORRENTE NAO ENCONTRADA");
            return;
        }

        despesaRecorrenteDAO.deletarDespesaRecorrente(despesaRecorrente);
        System.out.println("\n\nDESPESA RECORRENTE DELETADA COM SUCESSO");
    }
}
