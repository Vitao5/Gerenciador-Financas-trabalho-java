package com.mycompany.sistemafinanceiro.MODEL;

public class Despesa {
    private int idDespesa;
    private double valor;
    private String descricao;
    private String categoria;
    private int idUsuario;
    private Integer idFormaPagamento;

    public Despesa(int idDespesa, double valor, String descricao, String categoria, int idUsuario,
            Integer idFormaPagamento) {
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.idFormaPagamento = idFormaPagamento;
    }

    public Despesa(double valor, String descricao, String categoria, int idUsuario, Integer idFormaPagamento) {
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.idFormaPagamento = idFormaPagamento;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }
}
