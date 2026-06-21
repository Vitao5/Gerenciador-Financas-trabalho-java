package com.mycompany.sistemafinanceiro.BO;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.sistemafinanceiro.DAO.DespesExtraDAO;
import com.mycompany.sistemafinanceiro.DAO.UsuarioDAO;
import com.mycompany.sistemafinanceiro.MODEL.DespesaExtra;
import com.mycompany.sistemafinanceiro.util.DespesExtraUtil;
import com.mycompany.sistemafinanceiro.util.UsuarioUtil;

public class DespesaExtraBO {
    DespesExtraDAO despesExtraDAO;
    UsuarioDAO usuarioDAO;
    UsuarioUtil usuarioUtil;
    DespesExtraUtil despesExtraUtil;
    FormaPagamentoBO formaPagamentoBO;

    public DespesaExtraBO() {
        despesExtraDAO = new DespesExtraDAO();
        usuarioDAO = new UsuarioDAO();
        usuarioUtil = new UsuarioUtil();
        despesExtraUtil = new DespesExtraUtil();
        formaPagamentoBO = new FormaPagamentoBO();
    }

    public void cadastrarDespesaExtra(DespesaExtra despesaExtra, String cpf) throws SQLException {
        if (usuarioUtil.buscaUsuarioPorCPFUtil(cpf) == null) {
            System.out.println("\n\nUSUARIO NAO EXISTE AO TENTAR CADASTRAR UMA DESPESA");
            return;
        }

        if (DespesExtraUtil.validaObjetoDespesaExtra(despesaExtra)) {
            despesExtraDAO.salvarDespesaExtra(despesaExtra);
            System.out.println("\n\nDESPESA EXTRA CADASTRADA COM SUCESSO");
        } else {
            System.out.println("\n\nDESPESA EXTRA NAO CADASTRADA");
        }
    }

    public List<DespesaExtra> listarDespesasExtras(String cpf, int idUsuario) throws SQLException {

        if (usuarioUtil.buscaUsuarioPorCPFUtil(cpf) == null) {
            System.out.println("\n\nUSUARIO NAO EXISTE AO TENTAR LISTAR DESPESAS EXTRAS");
            return null;
        }

        return despesExtraDAO.buscarDespesasExtrasPorUsuario(idUsuario);
    }

    public DespesaExtra buscarDespesaExtraPorId(int idDespesaExtra) throws SQLException {
        DespesaExtra despesaExtra = despesExtraDAO.buscarDespesaExtra(idDespesaExtra);
        if (despesaExtra == null) {
            System.out.println("\n\nDESPESA EXTRA NAO ENCONTRADA");
            return null;
        }

        return despesaExtra;
    }

    public void atualizarDespesaExtra(DespesaExtra despesaExtra) throws SQLException {
        if (despesExtraDAO.buscarDespesaExtra(despesaExtra.getIdDespesa()) == null) {
            System.out.println("\n\nDESPESA EXTRA NAO ENCONTRADA");
            return;
        }

        if (DespesExtraUtil.validaObjetoDespesaExtra(despesaExtra)) {
            despesExtraDAO.atualizarDespesaExtra(despesaExtra);
            System.out.println("\n\nDESPESA EXTRA ATUALIZADA COM SUCESSO");
        } else {
            System.out.println("\n\nDESPESA EXTRA NAO ATUALIZADA");
        }
    }

    public void deletarDespesaExtra(DespesaExtra despesaExtra) throws SQLException {
        if (despesExtraDAO.buscarDespesaExtra(despesaExtra.getIdDespesa()) == null) {
            System.out.println("\n\nDESPESA EXTRA NAO ENCONTRADA");
            return;
        }

        despesExtraDAO.deletarDespesaExtra(despesaExtra);
        System.out.println("\n\nDESPESA EXTRA DELETADA COM SUCESSO");
    }
}
