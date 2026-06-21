package com.mycompany.sistemafinanceiro.util;

import java.sql.SQLException;

import com.mycompany.sistemafinanceiro.DAO.UsuarioDAO;
import com.mycompany.sistemafinanceiro.MODEL.Usuario;

public class UsuarioUtil {
    UsuarioDAO usuarioDAO;

    public UsuarioUtil() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean validaObjetoUsuario(Usuario user) {
        if (user.getCpf() == null || user.getCpf().isEmpty() ||
                user.getNome() == null || user.getNome().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getSenha() == null || user.getSenha().isEmpty() ||
                user.getCpf().length() != 11) {
            return false;
        }
        return true;
    }

    public Usuario buscaUsuarioPorCPFUtil(String cpf) throws SQLException {
        return usuarioDAO.buscarUsuarioPorCPF(cpf);
    }
}
