package com.mycompany.sistemafinanceiro.MENU;

import com.mycompany.sistemafinanceiro.BO.UsuarioBO;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;
import com.mycompany.sistemafinanceiro.util.UsuarioUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioMenu {

    private final Scanner sc;
    UsuarioUtil util = new UsuarioUtil();
    UsuarioBO urn = new UsuarioBO();
    DespesaExtraMenu despesaExtraMenu = new DespesaExtraMenu();
    DespesaRecorrenteMenu despesaRecorrenteMenu = new DespesaRecorrenteMenu();

    Usuario usuario = new Usuario(0, null, null, null, null);

    public UsuarioMenu(Scanner sc) {
        this.sc = sc;
    }

    public void realizaLogin() throws SQLException {

        System.out.println("================ LOGIN ================");

        System.out.print("\nDIGITE SEU EMAIL: ");
        String email = sc.nextLine();

        System.out.print("\nDIGITE SUA SENHA: ");
        String senha = sc.nextLine();

        Usuario dados = urn.realizarLoginRN(email, senha);

        while (dados == null) {
            System.out.println("\nEMAIL OU SENHA INCORRETOS. TENTE NOVAMENTE");
            System.out.print("\nDIGITE SEU EMAIL: ");
            email = sc.nextLine();

            System.out.print("\nDIGITE SUA SENHA: ");
            senha = sc.nextLine();

            dados = urn.realizarLoginRN(email, senha);
        }

        usuario.setCpf(dados.getCpf());
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        usuario.setIdUsuario(dados.getIdUsuario());

        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.menuPrincipal(usuario, sc);
    }

    public void cadastrarUsuario() throws SQLException {
        System.out.println("================ CADASTRO DE USUARIO ================");

        System.out.print("\nDIGITE SEU CPF: ");
        String cpf = sc.nextLine();

        while (urn.buscarUsuarioPorcpfRN(cpf) != null) {
            System.out.println("\nCPF JA CADASTRADO");
            System.out.print("\nDIGITE SEU CPF: ");
            cpf = sc.nextLine();
        }

        System.out.print("DIGITE SEU NOME: ");
        String nome = sc.nextLine();

        System.out.print("DIGITE SEU EMAIL: ");
        String email = sc.nextLine();

        while (urn.buscarUsuarioPorEmail(email) != null) {
            System.out.println("\nEMAIL JA CADASTRADO");
            System.out.print("\nDIGITE SEU EMAIL: ");
            email = sc.nextLine();
        }

        System.out.print("\nDIGITE SUA SENHA: ");
        String senha = sc.nextLine();

        System.out.print("\nREPITA A SENHA: ");
        String confirmSenha = sc.nextLine();

        while (!senha.equals(confirmSenha)) {
            System.out.println("\nAS SENHAS DIGITADAS NAO SAO IGUAIS. TENTE NOVAMENTE");
            System.out.print("\nDIGITE SUA SENHA: ");
            senha = sc.nextLine();

            System.out.print("\nREPITA A SENHA: ");
            confirmSenha = sc.nextLine();
        }

        urn.cadastrarUsuario(new Usuario(cpf, nome, email, senha));

        realizaLogin();
    }

    public void exluirConta(Usuario usuario, Scanner sc) throws SQLException {

        System.out.println("\nTEM CERTEZA QUE DESEJA CONTINUAR?\n");
        System.out.println("1 - SIM");
        System.out.println("2 - NAO");

        System.out.print("DIGITE A OPCAO DESEJADA: ");
        while (!sc.hasNextInt()) {
            System.out.print("DIGITE APENAS NUMEROS: ");
            sc.nextLine();
        }

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                urn.deletarUsuario(usuario);
                System.out.println("CONTA EXCLUIDA COM SUCESSO");
                System.out.println("SAINDO DO SISTEMA");
                System.exit(0);
                break;
            case 2:
                System.out.println("OPERACAO CANCELADA\n");
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.menuPrincipal(usuario, sc);
                break;
            default:
                System.out.println("OPCAO INVALIDA");
                break;
        }

    }

    public void editarDados(Usuario usuario, Scanner sc) throws SQLException {
        System.out.println("================ EDITAR DADOS ================\n\n");
        System.out.print("DIGITE O NOVO NOME: ");
        String nome = sc.nextLine();

        System.out.print("DIGITE O NOVO EMAIL: ");
        String email = sc.nextLine();

        System.out.print("DIGITE A NOVA SENHA: ");
        String senha = sc.nextLine();

        System.out.print("DIGITE A NOVA SENHA NOVAMENTE: ");
        String confirmSenha = sc.nextLine();

        while (!senha.equals(confirmSenha)) {
            System.out.println("AS SENHAS NAO SAO IGUAIS. TENTE NOVAMENTE");
            System.out.print("DIGITE A NOVA SENHA: ");
            senha = sc.nextLine();

            System.out.print("DIGITE A NOVA SENHA NOVAMENTE: ");
            confirmSenha = sc.nextLine();
        }

        Usuario dados = urn.atualizarUsuario(new Usuario(usuario.getIdUsuario(), usuario.getCpf(), nome, email, senha));

        if (dados != null) {
            // aqui eu reaproveito a instacia recebida e atualizo os dados dos usuario

            usuario.setNome(dados.getNome());
            usuario.setEmail(dados.getEmail());
            usuario.setSenha(dados.getSenha());
            System.out.println("DADOS ATUALIZADOS COM SUCESSO");
        } else {
            System.out.println("ERRO AO ATUALIZAR DADOS");
        }

    }

    public void editarPerfil(Usuario usuario, Scanner sc) throws SQLException {
        while (true) {
            System.out.println("\n================ PERFIL ================");
            System.out.println("\n1 - EDITAR DADOS");
            System.out.println("2 - EXCLUIR CONTA");
            System.out.println("3 - MENU PRINCIPAL");
            System.out.println("4 - VER INFORMACOES DA CONTA");
            System.out.println("5 - SAIR DO SISTEMA");
            System.out.print("DIGITE A OPCAO DESEJADA: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    editarDados(usuario, sc);
                    break;
                case 2:
                    exluirConta(usuario, sc);
                    break;
                case 3:
                    return; // aqui eu fiz para retornar ao menu principal com return para evitar
                // chamada de menus errados na pilha de execução
                case 4:
                    Usuario dados = urn.buscarUsuarioPorcpfRN(usuario.getCpf());

                    System.out.println("\n\nCPF: " + dados.getCpf());
                    System.out.println("NOME: " + dados.getNome());
                    System.out.println("EMAIL: " + dados.getEmail() + "\n\n");
                    break;
                case 5:
                    System.out.println("SAINDO DO SISTEMA");
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPCAO INVALIDA");
                    break;
            }
        }
    }

}
