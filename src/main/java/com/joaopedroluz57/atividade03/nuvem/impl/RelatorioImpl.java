package com.joaopedroluz57.atividade03.nuvem.impl;

import com.joaopedroluz57.atividade03.encriptacao.encriptador.FalhaEncriptacao;
import com.joaopedroluz57.atividade03.encriptacao.impl.EncriptadorImpl;
import com.joaopedroluz57.atividade03.modelo.pacote.Pacote;
import com.joaopedroluz57.atividade03.nuvem.relatorio.Relatorio;

import java.util.List;

public class RelatorioImpl implements Relatorio {

    /*
     * Geracao de relatorio
     * <p>
     * a complexidade e linear ja que nao possui loops alinhados.
     *
     */
    @Override
    public void gerarRelatorio(List<String> pacotesEspeciaisEncontrados, EncriptadorImpl encriptador) throws FalhaEncriptacao {
        for (String pacote : pacotesEspeciaisEncontrados) {
            System.out.println("Pacote encriptado: " + pacote);
//            System.out.println("Pacote desencriptado: " + encriptador.desencriptar(pacote));
        }
    }

}
