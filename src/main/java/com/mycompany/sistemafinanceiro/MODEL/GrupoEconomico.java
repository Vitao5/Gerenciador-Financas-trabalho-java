package com.mycompany.sistemafinanceiro.MODEL;

import java.util.ArrayList;
import java.util.List;

public class GrupoEconomico {
    private int idGrupo;
    private String nomeGrupo;
    private int idAdmin;
    private List<Usuario> participantes;

    public GrupoEconomico(int idGrupo, String nomeGrupo, int idAdmin) {
        this.idGrupo = idGrupo;
        this.nomeGrupo = nomeGrupo;
        this.idAdmin = idAdmin;
        this.participantes = new ArrayList<>();
    }

    public GrupoEconomico(String nomeGrupo, int idAdmin) {
        this.nomeGrupo = nomeGrupo;
        this.idAdmin = idAdmin;
        this.participantes = new ArrayList<>();
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
}
