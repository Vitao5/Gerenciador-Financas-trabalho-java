package com.mycompany.sistemafinanceiro.util;

import com.mycompany.sistemafinanceiro.MODEL.DespesaRecorrente;

public class DespesaRecorrenteUtil {
    public static boolean validaObjetoDespesaRecorrente(DespesaRecorrente despesaRecorrente) {
        if (despesaRecorrente.getDescricao() == null || despesaRecorrente.getDescricao().isEmpty() ||
                despesaRecorrente.getCategoria() == null || despesaRecorrente.getCategoria().isEmpty() ||
                despesaRecorrente.getIdUsuario() == 0 ||
                despesaRecorrente.getIdFormaPagamento() == null || despesaRecorrente.getValor() < 0 ||
                despesaRecorrente.getValorMensalidade() == null || despesaRecorrente.getValorMensalidade() < 0) {
            return false;
        }
        return true;
    }
}
