package com.mycompany.sistemafinanceiro.MODEL;

import java.util.List;

public class Usuario {
    private int idUsuario;
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    private List<DespesaExtra> despesasExtra;
    private List<DespesaRecorrente> despesasRecorrentes;

    public Usuario(int idUsuario, String cpf, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<DespesaExtra> getDespesasExtra() {
        return despesasExtra;
    }

    public void setDespesasExtra(List<DespesaExtra> despesasExtra) {
        this.despesasExtra = despesasExtra;
    }

    public List<DespesaRecorrente> getDespesasRecorrentes() {
        return despesasRecorrentes;
    }

    public void setDespesasRecorrentes(List<DespesaRecorrente> despesasRecorrentes) {
        this.despesasRecorrentes = despesasRecorrentes;
    }
}
