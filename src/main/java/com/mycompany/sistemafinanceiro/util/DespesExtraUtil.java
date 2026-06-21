package com.mycompany.sistemafinanceiro.util;

import com.mycompany.sistemafinanceiro.MODEL.DespesaExtra;

public class DespesExtraUtil {

    public static boolean validaObjetoDespesaExtra(DespesaExtra despesaExtra) {
        if (despesaExtra.getDescricao() == null || despesaExtra.getDescricao().isEmpty() ||
                despesaExtra.getCategoria() == null || despesaExtra.getCategoria().isEmpty() ||
                despesaExtra.getIdUsuario() == 0 ||
                despesaExtra.getIdFormaPagamento() == null || despesaExtra.getValor() < 0) {
            return false;
        }
        return true;
    }

}
