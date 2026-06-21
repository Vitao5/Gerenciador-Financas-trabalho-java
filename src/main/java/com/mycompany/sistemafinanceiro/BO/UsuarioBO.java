package com.mycompany.sistemafinanceiro.BO;

import java.sql.SQLException;

import com.mycompany.sistemafinanceiro.DAO.UsuarioDAO;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;
import com.mycompany.sistemafinanceiro.util.UsuarioUtil;

public class UsuarioBO {
    UsuarioDAO usarioDAO;
    UsuarioUtil util;

    public UsuarioBO() {
        usarioDAO = new UsuarioDAO();
        util = new UsuarioUtil();
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        if (!util.validaObjetoUsuario(usuario)) {
            return;
        }

        if (usarioDAO.buscarUsuario(usuario.getEmail()) != null) {
            System.out.println("\nEMAIL JA CADASTRADO. REALIZE OU LOGIN OU ESCOLHA OUTRO\n");
            return;
        }

        usarioDAO.salvarUsuario(usuario);

        System.out.println("\nUSUARIO CADASTRADO COM SUCESSO\n");
    }

    public Usuario atualizarUsuario(Usuario usuario) throws SQLException {
        if (!util.validaObjetoUsuario(usuario)) {
            System.out.println("\ncampos invalidos dentro do objeto usuario no metodo atualizarUsuario\n");
            return null;
        }

        if (usarioDAO.buscarUsuarioPorCPF(usuario.getCpf()) == null) {
            System.out.println("\nUSUARIO NAO ENCONTRADO\n");
            return null;
        }

        usarioDAO.atualizarUsuario(usuario);

        System.out.println("\nUSUARIO ATUALIZADO COM SUCESSO\n");
        return usarioDAO.buscarUsuarioPorCPF(usuario.getCpf());
    }

    public void deletarUsuario(Usuario usuario) throws SQLException {
        if (!util.validaObjetoUsuario(usuario)) {
            System.out.println("\ncampos invalidos dentro do objeto usuario no metodo deletarUsuario\n");
            return;
        }

        if (usarioDAO.buscarUsuarioPorCPF(usuario.getCpf()) == null) {
            System.out.println("\nUSUARIO NAO ENCONTRADO\n");
            return;
        }

        usarioDAO.deletarUsuario(usuario.getIdUsuario());

        System.out.println("\nUSUARIO DELETADO COM SUCESSO\n");
    }

    public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
        return usarioDAO.buscarUsuario(email);
    }

    public Usuario buscarUsuarioPorID(int id) throws SQLException {
        return usarioDAO.buscarUsuarioPorID(id);
    }

    public Usuario buscarUsuarioPorcpfRN(String cpf) throws SQLException {
        return usarioDAO.buscarUsuarioPorCPF(cpf);
    }

    public Usuario realizarLoginRN(String email, String senha) throws SQLException {
        if (email == null) {
            System.out.println("\nEMAIL INVALIDO\n");
            return null;
        }

        if (senha == null) {
            System.out.println("\nSENHA INVALIDA\n");
            return null;
        }

        Usuario usuario = usarioDAO.buscarUsuario(email);

        if (usuario == null) {
            return null;
        }

        if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
            return usuario;
        } else {
            return null;
        }
    }
}
