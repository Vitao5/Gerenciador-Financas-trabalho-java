package com.mycompany.sistemafinanceiro.MENU;

import com.mycompany.sistemafinanceiro.BO.FormaPagamentoBO;
import com.mycompany.sistemafinanceiro.MODEL.FormaPagamento;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FormaPagamentoMenu {

    FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();

    public void cadastrarFormaPagamento(Usuario usuario, Scanner sc) throws SQLException {
        System.out.println("\n================ CADASTRO DE FORMA DE PAGAMENTO ================");

        System.out.print("\nDIGITE A DESCRICAO DA FORMA DE PAGAMENTO: ");
        String descricao = sc.nextLine();

        try {
            FormaPagamento formaPagamento = new FormaPagamento(descricao, usuario.getIdUsuario());
            formaPagamentoBO.cadastrarFormaPagamento(formaPagamento);
            System.out.println("\nFORMA DE PAGAMENTO CADASTRADA COM SUCESSO!");
        } catch (SQLException e) {
            System.out.println("\nERRO: " + e.getMessage());
        }
    }

    public void listarFormasPagamento(Usuario usuario, Scanner sc) throws SQLException {
        List<FormaPagamento> lista = formaPagamentoBO.buscarFormasPagamentoPorUsuario(usuario.getIdUsuario());

        if (lista == null || lista.isEmpty()) {
            System.out.println("\nNENHUMA FORMA DE PAGAMENTO ENCONTRADA");
            return;
        }

        System.out.println("\n================= FORMAS DE PAGAMENTO =================");
        for (FormaPagamento fp : lista) {
            System.out.println("ID: " + fp.getIdFormaPagamento() + " | " + fp.getDescricao());
        }
    }

    public void buscarFormaPagamentoPorId(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DA FORMA DE PAGAMENTO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idFormaPagamento = sc.nextInt();
        sc.nextLine();

        FormaPagamento fp = formaPagamentoBO.buscarFormaPagamento(idFormaPagamento, usuario.getIdUsuario());

        if (fp != null) {
            System.out.println("\n================= FORMA DE PAGAMENTO =================");
            System.out.println("ID: " + fp.getIdFormaPagamento());
            System.out.println("DESCRICAO: " + fp.getDescricao());
        } else {
            System.out.println("\nFORMA DE PAGAMENTO NAO ENCONTRADA");
        }
    }

    public void atualizarFormaPagamento(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DA FORMA DE PAGAMENTO A ATUALIZAR: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idFormaPagamento = sc.nextInt();
        sc.nextLine();

        FormaPagamento fp = formaPagamentoBO.buscarFormaPagamento(idFormaPagamento, usuario.getIdUsuario());
        if (fp == null) {
            System.out.println("\nFORMA DE PAGAMENTO NAO ENCONTRADA");
            return;
        }

        System.out.println("\nDESCRICAO ATUAL: " + fp.getDescricao());
        System.out.print("DIGITE A NOVA DESCRICAO: ");
        String novaDescricao = sc.nextLine();

        fp.setDescricao(novaDescricao);

        try {
            formaPagamentoBO.atualizarFormaPagamento(fp);
            System.out.println("\nFORMA DE PAGAMENTO ATUALIZADA COM SUCESSO!");
        } catch (SQLException e) {
            System.out.println("\nERRO: " + e.getMessage());
        }
    }

    public void deletarFormaPagamento(Usuario usuario, Scanner sc) throws SQLException {
        System.out.print("\nDIGITE O ID DA FORMA DE PAGAMENTO A DELETAR: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int idFormaPagamento = sc.nextInt();
        sc.nextLine();

        FormaPagamento fp = formaPagamentoBO.buscarFormaPagamento(idFormaPagamento, usuario.getIdUsuario());
        if (fp == null) {
            System.out.println("\nFORMA DE PAGAMENTO NAO ENCONTRADA");
            return;
        }

        System.out.println("\nTEM CERTEZA QUE DESEJA DELETAR '" + fp.getDescricao() + "'?");
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
            try {
                formaPagamentoBO.deletarFormaPagamento(fp);
                System.out.println("\nFORMA DE PAGAMENTO DELETADA COM SUCESSO!");
            } catch (SQLException e) {
                System.out.println("\nERRO: " + e.getMessage());
            }
        } else {
            System.out.println("\nOPERACAO CANCELADA");
        }
    }

    public void menuFormaPagamento(Usuario usuario, Scanner sc) throws SQLException {
        int opcaoSelecionada = -1;

        while (opcaoSelecionada != 0) {
            System.out.println("\n ================= FORMA DE PAGAMENTO MENU =================");

            System.out.println("\nESCOLHA UMA OPCAO:");
            System.out.println("1 - CADASTRAR FORMA DE PAGAMENTO");
            System.out.println("2 - LISTAR FORMAS DE PAGAMENTO");
            System.out.println("3 - BUSCAR FORMA DE PAGAMENTO POR ID");
            System.out.println("4 - ATUALIZAR FORMA DE PAGAMENTO");
            System.out.println("5 - DELETAR FORMA DE PAGAMENTO");
            System.out.println("6 - VOLTAR AO MENU PRINCIPAL");
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
                    cadastrarFormaPagamento(usuario, sc);
                    break;
                case 2:
                    listarFormasPagamento(usuario, sc);
                    break;
                case 3:
                    buscarFormaPagamentoPorId(usuario, sc);
                    break;
                case 4:
                    atualizarFormaPagamento(usuario, sc);
                    break;
                case 5:
                    deletarFormaPagamento(usuario, sc);
                    break;
                case 6:
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
