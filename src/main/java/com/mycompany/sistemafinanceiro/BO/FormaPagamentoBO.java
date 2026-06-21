package com.mycompany.sistemafinanceiro.BO;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.sistemafinanceiro.DAO.FormaPagamentoDAO;
import com.mycompany.sistemafinanceiro.MODEL.FormaPagamento;

public class FormaPagamentoBO {
    FormaPagamentoDAO formaPagamentoDAO;

    public FormaPagamentoBO() {
        formaPagamentoDAO = new FormaPagamentoDAO();
    }

    public FormaPagamento cadastrarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        if (formaPagamento.getDescricao() == null || formaPagamento.getDescricao().trim().isEmpty()) {
            throw new SQLException("A DESCRICAO DA FORMA DE PAGAMENTO NAO PODE SER VAZIA");
        }
        return formaPagamentoDAO.cadastrarFormaPagamento(formaPagamento);
    }

    public FormaPagamento atualizarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        if (formaPagamento.getDescricao() == null || formaPagamento.getDescricao().trim().isEmpty()) {
            throw new SQLException("A DESCRICAO DA FORMA DE PAGAMENTO NAO PODE SER VAZIA");
        }
        return formaPagamentoDAO.atualizarFormaPagamento(formaPagamento);
    }

    public FormaPagamento deletarFormaPagamento(FormaPagamento formaPagamento) throws SQLException {
        return formaPagamentoDAO.deletarFormaPagamento(formaPagamento);
    }

    public FormaPagamento buscarFormaPagamento(int idFormaPagamento, int idUsuario) throws SQLException {
        return formaPagamentoDAO.buscarFormaPagamentoPorIdEUsuario(idFormaPagamento, idUsuario);
    }

    public List<FormaPagamento> buscarFormasPagamentoPorUsuario(int idUsuario) throws SQLException {
        return formaPagamentoDAO.buscarFormasPagamentoPorUsuario(idUsuario);
    }

    public void mostrarFormasDePagamento(int idUsuario) throws SQLException {
        List<FormaPagamento> listaFormaPagamento = formaPagamentoDAO.buscarFormasPagamentoPorUsuario(idUsuario);

        if (listaFormaPagamento != null && !listaFormaPagamento.isEmpty()) {
            System.out.println("\n\n");
            for (FormaPagamento formaPagamento : listaFormaPagamento) {
                System.out.println(formaPagamento.getIdFormaPagamento() + " - " + formaPagamento.getDescricao());
            }

            System.out.println("\n\n");
        } else {
            System.out.println("\nNENHUMA FORMA DE PAGAMENTO CADASTRADA PARA ESTE USUARIO");
        }

    }

}
