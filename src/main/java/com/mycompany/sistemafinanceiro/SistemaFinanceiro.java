package com.mycompany.sistemafinanceiro;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mycompany.sistemafinanceiro.MENU.UsuarioMenu;

public class SistemaFinanceiro {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        UsuarioMenu menu = new UsuarioMenu(sc);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=============== FISCALIZE FINANCAS ===============");
            System.out.println("\nESCOLHA UMA OPCAO PARA CONTINUAR:");
            System.out.println("1 - REALIZAR LOGIN");
            System.out.println("2 - CADASTRAR USUARIO");
            System.out.println("0 - SAIR");
            System.out.print("\nOPCAO SELECIONADA: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nOPCAO INVALIDA. DIGITE UM NUMERO.");
                continue;
            }

            switch (opcao) {
                case 1:
                    menu.realizaLogin();
                    break;
                case 2:
                    menu.cadastrarUsuario();
                    break;
                case 0:
                    System.out.println("\nSAINDO DO SISTEMA");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOPCAO INVALIDA. TENTE NOVAMENTE");
                    break;
            }
        }

        sc.close();
    }
}
