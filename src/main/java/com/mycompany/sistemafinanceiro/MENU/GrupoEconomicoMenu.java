package com.mycompany.sistemafinanceiro.MENU;

import com.mycompany.sistemafinanceiro.BO.GrupoEconomicoBO;
import com.mycompany.sistemafinanceiro.BO.UsuarioBO;
import com.mycompany.sistemafinanceiro.DAO.UsuarioDAO;
import com.mycompany.sistemafinanceiro.MODEL.Despesa;
import com.mycompany.sistemafinanceiro.MODEL.GrupoEconomico;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GrupoEconomicoMenu {

    GrupoEconomicoBO grupoEconomicoBO = new GrupoEconomicoBO();
    UsuarioBO usuariaBO = new UsuarioBO();

    public void cadastrarGrupoEconomico(Usuario usuario, Scanner sc) throws SQLException {
        System.out.println("\n================ CADASTRO DE GRUPO ECONOMICO ================");

        System.out.print("\nDIGITE O NOME DO GRUPO ECONOMICO: ");
        String nomeGrupo = sc.nextLine();

        GrupoEconomico grupoEconomico = new GrupoEconomico(nomeGrupo, usuario.getIdUsuario());

        GrupoEconomico grupoCriado = grupoEconomicoBO.cadastrarGrupoEconomico(grupoEconomico);

        if (grupoCriado != null) {
            System.out.println("\nGRUPO ECONOMICO CRIADO COM SUCESSO!");
        } else {
            System.out.println("\nERRO AO CRIAR GRUPO ECONOMICO");
        }
    }

    public void listarGruposEconomicos(Usuario usuario, Scanner sc) throws SQLException {
        List<GrupoEconomico> lista = grupoEconomicoBO.buscarGruposUsuarioParticipanteBO(usuario.getIdUsuario());

        if (lista == null || lista.isEmpty()) {
            System.out.println("\n\nNENHUM GRUPO ECONOMICO ENCONTRADO");
            return;
        }

        System.out.println("\n\n================= LISTA DE GRUPOS ECONOMICOS =================");
        for (GrupoEconomico g : lista) {
            System.out.println("\nID: " + g.getIdGrupo());
            System.out.println("Nome: " + g.getNomeGrupo());
            System.out.println("ID Admin: " + g.getIdAdmin());

            List<Usuario> participantes = grupoEconomicoBO.buscarParticipantes(g.getIdGrupo());
            if (participantes.isEmpty()) {
                System.out.println("Participantes: NENHUM");
            } else {
                for (Usuario p : participantes) {
                    System.out.println("Nome do Participante: " + p.getNome() + " - ID: " + p.getIdUsuario());
                }
            }
        }
    }

    public void buscarGrupoEconomicoPorId(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO ECONOMICO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());

        if (grupo != null) {
            System.out.println("\n================= GRUPO ECONOMICO =================");
            System.out.println("ID: " + grupo.getIdGrupo());
            System.out.println("Nome: " + grupo.getNomeGrupo());
            System.out.println("ID Admin: " + grupo.getIdAdmin());

            List<Usuario> participantes = grupoEconomicoBO.buscarParticipantes(grupo.getIdGrupo());
            if (participantes.isEmpty()) {
                System.out.println("Participantes: NENHUM");
            } else {
                System.out.println("Participantes: \n");
                for (Usuario p : participantes) {
                    System.out.println(p.getNome() + " - ID: " + p.getIdUsuario());
                }
            }
        } else {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
        }
    }

    public void atualizarGrupoEconomico(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO ECONOMICO A ATUALIZAR: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        if (grupo.getIdAdmin() != usuario.getIdUsuario()) {
            System.out.println("\nVOCE NAO TEM PERMISSAO PARA ATUALIZAR ESTE GRUPO. APENAS O ADMIN PODE FAZER ISSO.");
            return;
        }

        System.out.print("DIGITE O NOVO NOME DO GRUPO: ");
        String novoNome = sc.nextLine();

        grupo.setNomeGrupo(novoNome);

        GrupoEconomico grupoAtualizado = grupoEconomicoBO.atualizarGrupoEconomico(grupo);

        if (grupoAtualizado != null) {
            System.out.println("\nGRUPO ECONOMICO ATUALIZADO COM SUCESSO!");
        } else {
            System.out.println("\nERRO AO ATUALIZAR GRUPO ECONOMICO");
        }
    }

    public void deletarGrupoEconomico(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO ECONOMICO A DELETAR: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        if (grupo.getIdAdmin() != usuario.getIdUsuario()) {
            System.out.println("\nVOCE NAO TEM PERMISSAO PARA DELETAR ESTE GRUPO. APENAS O ADMIN PODE FAZER ISSO.");
            return;
        }

        System.out.println("\nTEM CERTEZA QUE DESEJA DELETAR O GRUPO '" + grupo.getNomeGrupo() + "'?");
        System.out.println("1 - SIM");
        System.out.println("2 - NAO");
        System.out.print("DIGITE A OPCAO DESEJADA: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            grupoEconomicoBO.deletarGrupoEconomico(grupo);
            System.out.println("\nGRUPO ECONOMICO DELETADO COM SUCESSO!");
        } else {
            System.out.println("\nOPERACAO CANCELADA");
        }
    }

    public void gerenciarParticipantes(Usuario usuario, Scanner sc) throws SQLException {
        int opcaoParticipante = -1;

        while (opcaoParticipante != 0) {
            System.out.println("\n================= GERENCIAR PARTICIPANTES =================");
            System.out.println("\nESCOLHA UMA OPCAO:");
            System.out.println("1 - ADICIONAR PARTICIPANTE");
            System.out.println("2 - REMOVER PARTICIPANTE");
            System.out.println("3 - LISTAR PARTICIPANTES DE UM GRUPO");
            System.out.println("0 - VOLTAR");
            System.out.print("DIGITE SUA OPCAO: ");

            try {
                opcaoParticipante = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("OPCAO INVALIDA. DIGITE UM NUMERO.");
                continue;
            }

            switch (opcaoParticipante) {
                case 1:
                    adicionarParticipante(usuario, sc);
                    break;
                case 2:
                    removerParticipante(usuario, sc);
                    break;
                case 3:
                    listarParticipantes(usuario, sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("OPCAO INVALIDA");
            }
        }
    }

    public void adicionarParticipante(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        if (grupo.getIdAdmin() != usuario.getIdUsuario()) {
            System.out.println("\nVOCE NAO TEM PERMISSAO. APENAS O ADMIN PODE ADICIONAR PARTICIPANTES.");
            return;
        }

        System.out.print("DIGITE O ID DO USUARIO A ADICIONAR: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idUsuario = sc.nextInt();
        sc.nextLine();

        try {
            grupoEconomicoBO.adicionarParticipante(idGrupo, idUsuario);
            System.out.println("\nPARTICIPANTE ADICIONADO COM SUCESSO!");
        } catch (SQLException e) {
            System.out.println("\nERRO: " + e.getMessage());
        }
    }

    public void removerParticipante(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        if (grupo.getIdAdmin() != usuario.getIdUsuario()) {
            System.out.println("\nVOCE NAO TEM PERMISSAO. APENAS O ADMIN PODE REMOVER PARTICIPANTES.");
            return;
        }

        List<Usuario> participantes = grupoEconomicoBO.buscarParticipantes(idGrupo);
        if (participantes.isEmpty()) {
            System.out.println("\nESTE GRUPO NAO TEM PARTICIPANTES");
            return;
        }

        System.out.println("\nPARTICIPANTES ATUAIS:");
        for (Usuario p : participantes) {
            System.out.println("  - " + p.getNome() + " (ID: " + p.getIdUsuario() + ")");
        }

        System.out.print("\nDIGITE O ID DO USUARIO A REMOVER: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idUsuario = sc.nextInt();
        sc.nextLine();

        try {
            grupoEconomicoBO.removerParticipante(idGrupo, idUsuario);
            System.out.println("\nPARTICIPANTE REMOVIDO COM SUCESSO!");
        } catch (SQLException e) {
            System.out.println("\nERRO: " + e.getMessage());
        }
    }

    public void listarParticipantes(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        List<Usuario> participantes = grupoEconomicoBO.buscarParticipantes(idGrupo);

        System.out.println("\n================= PARTICIPANTES DO GRUPO  =================");
        if (participantes.isEmpty()) {
            System.out.println("NENHUM PARTICIPANTE ENCONTRADO");
        } else {
            for (Usuario p : participantes) {
                System.out.println("NOME DO GRUPO: " + grupo.getNomeGrupo());
                System.out.println("EMAIL DO USUARIO: " + p.getEmail());
                System.out.println("NOME DO USUARIO: " + p.getNome());
                System.out.println("\n");

            }
        }
    }

    public void mostraDespesasDoGrupo(Usuario usuario, Scanner sc, int idGrupoDespesa) throws SQLException {
        System.out.print("\nDIGITE O ID DO GRUPO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idGrupo = sc.nextInt();
        sc.nextLine();

        GrupoEconomico grupo = grupoEconomicoBO.buscarGrupoEconomico(idGrupo, usuario.getIdUsuario());
        if (grupo == null) {
            System.out.println("\nGRUPO ECONOMICO NAO ENCONTRADO");
            return;
        }

        List<Despesa> despesas = grupoEconomicoBO.buscarDespesasDoGrupo(idGrupo);

        System.out.println("\n================= DESPESAS DO GRUPO =================");
        if (despesas.isEmpty()) {
            System.out.println("NENHUMA DESPESA ENCONTRADA");
        } else {
            Double valorGastoPeloGrupo = 0.0;
            for (Despesa d : despesas) {
                Usuario responsavel = usuariaBO.buscarUsuarioPorID(d.getIdUsuario());
                System.out.println("RESPONSAVEL: " + responsavel.getNome());
                System.out.println("ID DA DESPESA: " + d.getIdDespesa());
                System.out.println("TIPO: " + d.getCategoria());
                System.out.println("DESCRICAO: " + d.getDescricao());
                System.out.println("VALOR: " + d.getValor());
                System.out.println("----------------------------------------\n");
                valorGastoPeloGrupo += d.getValor();
            }

            System.out.println("TOTAL GASTO PELO GRUPO: " + valorGastoPeloGrupo + " reais\n");
        }
    }

    public void menuGrupoEconomico(Usuario usuario, Scanner sc) throws SQLException {
        int opcaoSelecionada = -1;

        while (opcaoSelecionada != 0) {
            System.out.println("\n ================= GRUPO ECONOMICO MENU =================");

            System.out.println("\nESCOLHA UMA OPCAO:");
            System.out.println("1 - CRIAR GRUPO ECONOMICO");
            System.out.println("2 - LISTAR GRUPOS ECONOMICOS");
            System.out.println("3 - BUSCAR GRUPO ECONOMICO POR ID");
            System.out.println("4 - ATUALIZAR GRUPO ECONOMICO");
            System.out.println("5 - DELETAR GRUPO ECONOMICO");
            System.out.println("6 - GERENCIAR PARTICIPANTES");
            System.out.println("7 - VOLTAR AO MENU PRINCIPAL");
            System.out.println("0 - SAIR");
            System.out.print("DIGITE SUA OPCAO: ");

            try {
                opcaoSelecionada = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("OPCAO INVALIDA. DIGITE UM NUMERO.");
                continue;
            }

            switch (opcaoSelecionada) {
                case 1:
                    cadastrarGrupoEconomico(usuario, sc);
                    break;
                case 2:
                    listarGruposEconomicos(usuario, sc);
                    break;
                case 3:
                    buscarGrupoEconomicoPorId(usuario, sc);
                    break;
                case 4:
                    atualizarGrupoEconomico(usuario, sc);
                    break;
                case 5:
                    deletarGrupoEconomico(usuario, sc);
                    break;
                case 6:
                    gerenciarParticipantes(usuario, sc);
                    break;
                case 7:
                    return;
                case 0:
                    System.out.println("SAINDO DO SISTEMA");
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPCAO INVALIDA");
            }
        }
    }
}
