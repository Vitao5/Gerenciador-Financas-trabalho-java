package com.mycompany.sistemafinanceiro.MODEL;

public class DespesaRecorrente extends Despesa {
    private Double valorMensalidade;

    public DespesaRecorrente(double valor, String descricao, String categoria, int idUsuario,
            Integer idFormaPagamento) {
        super(valor, descricao, categoria, idUsuario, idFormaPagamento);
    }

    public DespesaRecorrente(int idDespesa, double valor, String descricao, String categoria, int idUsuario,
            Integer idFormaPagamento, Double valorMensalidade) {
        super(idDespesa, valor, descricao, categoria, idUsuario, idFormaPagamento);
        this.valorMensalidade = valorMensalidade;
    }

    public Double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(Double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

}
