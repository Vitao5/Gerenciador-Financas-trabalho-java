package com.mycompany.sistemafinanceiro.MODEL;

public class FormaPagamento {
    private int idFormaPagamento;
    private String descricao;
    private int idUsuario;

    public FormaPagamento(int idFormaPagamento, String descricao, int idUsuario) {
        this.idFormaPagamento = idFormaPagamento;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public FormaPagamento(String descricao, int idUsuario) {
        this.descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
