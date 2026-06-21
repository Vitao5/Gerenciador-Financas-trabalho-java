package com.mycompany.sistemafinanceiro.MODEL;

public class DespesaExtra extends Despesa {

    public DespesaExtra(int idDespesa, double valor, String descricao, String categoria, int idUsuario,
            Integer idFormaPagamento) {
        super(idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento);
    }

    public DespesaExtra(double valor, String descricao, String categoria, int idUsuario,
            Integer idFormaPagamento) {
        super(valor, descricao, categoria, idUsuario, idFormaPagamento);
    }
}
