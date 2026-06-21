package com.mycompany.sistemafinanceiro.MENU;

import java.sql.SQLException;
import java.util.Scanner;

import com.mycompany.sistemafinanceiro.MODEL.Usuario;

public class MenuPrincipal {

    public void menuPrincipal(Usuario usuario, Scanner sc) throws SQLException {
        while (true) {
            System.out.println("\nESCOLHA UMA OPCAO:");
            System.out.println("1  ACESSAR MENU DESPESA EXTRA");
            System.out.println("2  ACESSAR MENU DESPESA RECORRENTE");
            System.out.println("3  ACESSAR MENU GRUPO ECONOMICO");
            System.out.println("4  ACESSAR MENU FORMA DE PAGAMENTO");
            System.out.println("5  EDITAR PERFIL");
            System.out.println("6  SAIR DO SISTEMA");
            System.out.print("DIGITE A OPCAO DESEJADA: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    DespesaExtraMenu despesaExtraMenu = new DespesaExtraMenu();
                    despesaExtraMenu.menuDespesaExtra(usuario, sc);
                    break;
                case 2:
                    DespesaRecorrenteMenu despesaRecorrenteMenu = new DespesaRecorrenteMenu();
                    despesaRecorrenteMenu.menuDespesaRecorrente(usuario, sc);
                    break;
                case 3:
                    GrupoEconomicoMenu grupoEconomicoMenu = new GrupoEconomicoMenu();
                    grupoEconomicoMenu.menuGrupoEconomico(usuario, sc);
                    break;
                case 4:
                    FormaPagamentoMenu formaPagamentoMenu = new FormaPagamentoMenu();
                    formaPagamentoMenu.menuFormaPagamento(usuario, sc);
                    break;
                case 5:
                    UsuarioMenu usuarioMenu = new UsuarioMenu(sc);
                    usuarioMenu.editarPerfil(usuario, sc);
                    break;
                case 6:
                    System.out.println("SAINDO DO SISTEMA");
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPCAO INVALIDA!");
                    break;
            }
        }
    }
}
