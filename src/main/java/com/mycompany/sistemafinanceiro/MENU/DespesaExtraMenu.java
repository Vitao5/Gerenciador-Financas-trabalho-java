package com.mycompany.sistemafinanceiro.MENU;

import com.mycompany.sistemafinanceiro.BO.DespesaExtraBO;
import com.mycompany.sistemafinanceiro.BO.FormaPagamentoBO;
import com.mycompany.sistemafinanceiro.MODEL.DespesaExtra;
import com.mycompany.sistemafinanceiro.MODEL.FormaPagamento;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DespesaExtraMenu {
    DespesaExtraBO despesExtraBO = new DespesaExtraBO();
    FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();
    Double saldoDevedorDespesas = 0.0;

    public void cadastrarDespesaExtra(Usuario usuario, Scanner sc) throws SQLException {
        List<FormaPagamento> listaFormaPagamento = formaPagamentoBO
                .buscarFormasPagamentoPorUsuario(usuario.getIdUsuario());

        if (listaFormaPagamento == null || listaFormaPagamento.isEmpty()) {
            System.out.println(
                    "\nNENHUMA FORMA DE PAGAMENTO CADASTRADA PARA ESTE USUARIO.\nCADASTRE UMA FORMA DE PAGAMENTO ANTES DE CADASTRAR UMA DESPESA\n");
            return;
        }

        System.out.print("DIGITE A DESCRICAO DA DESPESA EXTRA: ");
        String descricao = sc.nextLine();

        System.out.print("DIGITE A CATEGORIA DA DESPESA: ");
        String categoria = sc.nextLine();

        System.out.print("DIGITE O VALOR DA DESPESA: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }

        double valorDespesaExtra = Double.parseDouble(sc.nextLine().replace(",", "."));

        formaPagamentoBO.mostrarFormasDePagamento(usuario.getIdUsuario());

        System.out.print("DIGITE O ID DA FORMA DE PAGAMENTO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int formaPagamento = sc.nextInt();

        DespesaExtra despesaExtra = new DespesaExtra(valorDespesaExtra, descricao, categoria,
                usuario.getIdUsuario(), formaPagamento);

        despesExtraBO.cadastrarDespesaExtra(despesaExtra, usuario.getCpf());
    }

    public void listarDespesasExtras(Usuario usuario, Scanner sc) throws SQLException {
        List<DespesaExtra> lista = despesExtraBO.listarDespesasExtras(usuario.getCpf(), usuario.getIdUsuario());

        usuario.setDespesasExtra(null);
        usuario.setDespesasExtra(lista);

        if (lista == null || lista.isEmpty()) {
            System.out.println("\n\nNENHUMA DESPESA EXTRA ENCONTRADA");
            return;
        }

        System.out.println("\n\n================= LISTA DE DESPESAS EXTRAS =================");
        saldoDevedorDespesas = 0.0;
        for (DespesaExtra d : lista) {
            saldoDevedorDespesas = saldoDevedorDespesas + d.getValor();

            System.out.println("\n\nID: " + d.getIdDespesa());
            System.out.println("Descricao: " + d.getDescricao());
            System.out.println("Categoria: " + d.getCategoria());
            System.out.println("Valor: " + d.getValor());
        }
        System.out.println("\n\nSALDO DEVEDOR DAS DESPESAS EXTRAS: " + saldoDevedorDespesas);
    }

    public void buscarDespesaExtraPorId(Scanner sc) throws SQLException {

        System.out.print("DIGITE O ID DA DESPESA EXTRA: ");
        int idDespesaExtra = sc.nextInt();

        DespesaExtra despesaExtra = despesExtraBO.buscarDespesaExtraPorId(idDespesaExtra);

        if (despesaExtra != null) {
            System.out.println("\n\n================= DESPESA EXTRA =================");
            System.out.println("ID: " + despesaExtra.getIdDespesa());
            System.out.println("Descricao: " + despesaExtra.getDescricao());
            System.out.println("Categoria: " + despesaExtra.getCategoria());
            System.out.println("Valor: " + despesaExtra.getValor());
        }
    }

    public void atualizarDespesaExtra(Usuario usuario, Scanner sc) throws SQLException {
        List<FormaPagamento> listaFormaPagamento = formaPagamentoBO
                .buscarFormasPagamentoPorUsuario(usuario.getIdUsuario());

        if (listaFormaPagamento == null || listaFormaPagamento.isEmpty()) {
            System.out.println(
                    "\nNENHUMA FORMA DE PAGAMENTO CADASTRADA PARA ESTE USUARIO.\nCADASTRE UMA FORMA DE PAGAMENTO ANTES DE CADASTRAR UMA DESPESA\n");
            return;
        }

        System.out.print("DIGITE O ID DA DESPESA EXTRA A ATUALIZAR: ");
        int idDespesaExtra = sc.nextInt();

        DespesaExtra despesaExtra = despesExtraBO.buscarDespesaExtraPorId(idDespesaExtra);
        if (despesaExtra == null) {
            return;
        }

        sc.nextLine();

        System.out.print("DIGITE A NOVA DESCRICAO: ");
        String descricao = sc.nextLine();

        System.out.print("DIGITE A NOVA CATEGORIA: ");
        String categoria = sc.nextLine();

        System.out.print("DIGITE O NOVO VALOR: ");
        while (!sc.hasNextDouble()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        double valor = Double.parseDouble(sc.nextLine().replace(",", "."));

        formaPagamentoBO.mostrarFormasDePagamento(usuario.getIdUsuario());
        System.out.print("DIGITE O ID DA NOVA FORMA DE PAGAMENTO: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }
        int formaPagamento = sc.nextInt();

        despesaExtra.setDescricao(descricao);
        despesaExtra.setCategoria(categoria);
        despesaExtra.setValor(valor);
        despesaExtra.setIdFormaPagamento(formaPagamento);

        despesExtraBO.atualizarDespesaExtra(despesaExtra);
    }

    public void deletarDespesaExtra(Scanner sc) throws SQLException {

        System.out.print("DIGITE O ID DA DESPESA EXTRA A DELETAR: ");

        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }

        int idDespesaExtra = sc.nextInt();

        DespesaExtra despesaExtra = despesExtraBO.buscarDespesaExtraPorId(idDespesaExtra);
        if (despesaExtra == null) {
            return;
        }

        despesExtraBO.deletarDespesaExtra(despesaExtra);
    }

    public void menuDespesaExtra(Usuario usuario, Scanner sc) throws SQLException {
        int opcaoSelecionada = -1;

        while (opcaoSelecionada != 0) {
            System.out.println("\n ================= DESPESAS EXTRA MENU =================");

            System.out.println("\nESCOLHA UMA OPCAO:");
            System.out.println("1 - CADASTRAR DESPESA EXTRA");
            System.out.println("2 - LISTAR DESPESAS EXTRAS");
            System.out.println("3 - BUSCAR DESPESA EXTRA POR ID");
            System.out.println("4 - ATUALIZAR DESPESA EXTRA");
            System.out.println("5 - DELETAR DESPESA EXTRA");
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
                    cadastrarDespesaExtra(usuario, sc);
                    break;
                case 2:
                    listarDespesasExtras(usuario, sc);
                    break;
                case 3:
                    buscarDespesaExtraPorId(sc);
                    break;
                case 4:
                    atualizarDespesaExtra(usuario, sc);
                    break;
                case 5:
                    deletarDespesaExtra(sc);
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
