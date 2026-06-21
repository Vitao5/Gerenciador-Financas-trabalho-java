package com.mycompany.sistemafinanceiro.MENU;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.mycompany.sistemafinanceiro.BO.DespesaRecorrenteBO;
import com.mycompany.sistemafinanceiro.BO.FormaPagamentoBO;
import com.mycompany.sistemafinanceiro.MODEL.DespesaRecorrente;
import com.mycompany.sistemafinanceiro.MODEL.FormaPagamento;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

public class DespesaRecorrenteMenu {
    DespesaRecorrenteBO despesaRecorrenteBO = new DespesaRecorrenteBO();
    FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();
    Double saldoDevedorDespesasRecorrentes = 0.0;

    public void cadastrarDespesaRecorrente(Usuario usuario, Scanner sc) throws SQLException {
        List<FormaPagamento> listaFormaPagamento = formaPagamentoBO
                .buscarFormasPagamentoPorUsuario(usuario.getIdUsuario());

        if (listaFormaPagamento == null || listaFormaPagamento.isEmpty()) {
            System.out.println(
                    "\nNENHUMA FORMA DE PAGAMENTO CADASTRADA PARA ESTE USUARIO.\nCADASTRE UMA FORMA DE PAGAMENTO ANTES DE CADASTRAR UMA DESPESA\n");
            return;
        }

        System.out.print("DIGITE A DESCRICAO DA DESPESA RECORRENTE: ");
        String descricao = sc.nextLine();

        System.out.print("DIGITE A CATEGORIA DA DESPESA: ");
        String categoria = sc.nextLine();

        System.out.print("DIGITE O VALOR DA MENSALIDADE: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }

        formaPagamentoBO.mostrarFormasDePagamento(usuario.getIdUsuario());

        System.out.print("DIGITE O ID DA FORMA DE PAGAMENTO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int formaPagamento = sc.nextInt();

        System.out.print("DIGITE O VALOR DA MENSALIDADE: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        double valorMensalidade = Double.parseDouble(sc.nextLine().replace(",", "."));

        DespesaRecorrente despesaRecorrente = new DespesaRecorrente(
                valorMensalidade,
                descricao,
                categoria,
                usuario.getIdUsuario(),
                formaPagamento);

        despesaRecorrenteBO.cadastrarDespesaRecorrente(despesaRecorrente, usuario.getCpf());
    }

    public void listarDespesasRecorrentes(Usuario usuario, Scanner sc) throws SQLException {
        List<DespesaRecorrente> lista = despesaRecorrenteBO.listarDespesasRecorrentes(usuario.getCpf(),
                usuario.getIdUsuario());

        usuario.setDespesasRecorrentes(null);
        usuario.setDespesasRecorrentes(lista);

        if (lista == null || lista.isEmpty()) {
            System.out.println("\n\nNENHUMA DESPESA RECORRENTE ENCONTRADA");
            return;
        }

        System.out.println("\n\n================= LISTA DE DESPESAS RECORRENTES =================");
        saldoDevedorDespesasRecorrentes = 0.0;
        for (DespesaRecorrente d : lista) {
            saldoDevedorDespesasRecorrentes = saldoDevedorDespesasRecorrentes + d.getValor();

            System.out.println("\n\nID: " + d.getIdDespesa());
            System.out.println("Descricao: " + d.getDescricao());
            System.out.println("Categoria: " + d.getCategoria());
            System.out.println("Valor: " + d.getValor());
            System.out.println("Valor Mensalidade: " + d.getValorMensalidade());
        }
        System.out.println("\n\nSALDO DEVEDOR DAS DESPESAS RECORRENTES: " + saldoDevedorDespesasRecorrentes);
    }

    public void buscarDespesaRecorrentePorId(Scanner sc) throws SQLException {

        System.out.print("DIGITE O ID DA DESPESA RECORRENTE: ");
        int idDespesaRecorrente = sc.nextInt();

        DespesaRecorrente despesaRecorrente = despesaRecorrenteBO.buscarDespesaRecorrentePorId(idDespesaRecorrente);

        if (despesaRecorrente != null) {
            System.out.println("\n\n================= DESPESA RECORRENTE =================");
            System.out.println("ID: " + despesaRecorrente.getIdDespesa());
            System.out.println("Descricao: " + despesaRecorrente.getDescricao());
            System.out.println("Categoria: " + despesaRecorrente.getCategoria());
            System.out.println("Valor: " + despesaRecorrente.getValor());
            System.out.println("Valor Mensalidade: " + despesaRecorrente.getValorMensalidade());
        }
    }

    public void atualizarDespesaRecorrente(Usuario usuario, Scanner sc) throws SQLException {
        List<FormaPagamento> listaFormaPagamento = formaPagamentoBO
                .buscarFormasPagamentoPorUsuario(usuario.getIdUsuario());

        if (listaFormaPagamento == null || listaFormaPagamento.isEmpty()) {
            System.out.println(
                    "\nNENHUMA FORMA DE PAGAMENTO CADASTRADA PARA ESTE USUARIO.\nCADASTRE UMA FORMA DE PAGAMENTO ANTES DE CADASTRAR UMA DESPESA\n");
            return;
        }

        System.out.print("DIGITE O ID DA DESPESA RECORRENTE A ATUALIZAR: ");
        int idDespesaRecorrente = sc.nextInt();

        DespesaRecorrente despesaRecorrente = despesaRecorrenteBO.buscarDespesaRecorrentePorId(idDespesaRecorrente);
        if (despesaRecorrente == null) {
            return;
        }

        sc.nextLine();

        System.out.print("DIGITE A NOVA DESCRICAO: ");
        String descricao = sc.nextLine();

        System.out.print("DIGITE A NOVA CATEGORIA: ");
        String categoria = sc.nextLine();

        System.out.print("DIGITE O NOVO VALOR DA MENSALIDADE: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        double valor = sc.nextDouble();

        System.out.print("DIGITE O NOVO VALOR DA MENSALIDADE: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        double valorMensalidade = sc.nextDouble();

        formaPagamentoBO.mostrarFormasDePagamento(usuario.getIdUsuario());
        System.out.print("DIGITE O ID DA NOVA FORMA DE PAGAMENTO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int formaPagamento = sc.nextInt();

        despesaRecorrente.setDescricao(descricao);
        despesaRecorrente.setCategoria(categoria);
        despesaRecorrente.setValor(valor);
        despesaRecorrente.setValorMensalidade(valorMensalidade);
        despesaRecorrente.setIdFormaPagamento(formaPagamento);

        despesaRecorrenteBO.atualizarDespesaRecorrente(despesaRecorrente);
    }

    public void deletarDespesaRecorrente(Scanner sc) throws SQLException {

        System.out.print("DIGITE O ID DA DESPESA RECORRENTE A DELETAR: ");
        int idDespesaRecorrente = sc.nextInt();
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        DespesaRecorrente despesaRecorrente = despesaRecorrenteBO.buscarDespesaRecorrentePorId(idDespesaRecorrente);
        if (despesaRecorrente == null) {
            return;
        }

        despesaRecorrenteBO.deletarDespesaRecorrente(despesaRecorrente);
    }

    public void menuDespesaRecorrente(Usuario usuario, Scanner sc) throws SQLException {
        int opcaoSelecionada = -1;

        while (opcaoSelecionada != 0) {
            System.out.println("\n ================= DESPESAS RECORRENTES MENU =================");

            System.out.println("\n\nESCOLHA UMA OPCAO:");
            System.out.println("1 - CADASTRAR DESPESA RECORRENTE");
            System.out.println("2 - LISTAR DESPESAS RECORRENTES");
            System.out.println("3 - BUSCAR DESPESA RECORRENTE POR ID");
            System.out.println("4 - ATUALIZAR DESPESA RECORRENTE");
            System.out.println("5 - DELETAR DESPESA RECORRENTE");
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
                    cadastrarDespesaRecorrente(usuario, sc);
                    break;
                case 2:
                    listarDespesasRecorrentes(usuario, sc);
                    break;
                case 3:
                    buscarDespesaRecorrentePorId(sc);
                    break;
                case 4:
                    atualizarDespesaRecorrente(usuario, sc);
                    break;
                case 5:
                    deletarDespesaRecorrente(sc);
                    break;
                case 6:
                    return; // aqui eu fiz para retornar ao menu principal com return para evitar
                // chamada de menus errados na pilha de execução
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